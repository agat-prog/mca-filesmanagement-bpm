package mca.filesmanagement.bpm.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.bpm.BpmDummieFactory;
import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.service.BpmService;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Controller tests")
public class BpmControllerTest {

	@InjectMocks
	private BpmController bpmController;

	@Mock
	private BpmService bpmService;

	/** Inicialización de Mockito. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/** Test available phases list. */
	@Test
	@DisplayName("Test available phases list.")
	public void givenRequestAvailablePhasesWhenRequestThenReturnPhasesList() {
		long id = 1;
		when(this.bpmService.availablePhases(any()))
				.thenReturn(Arrays.asList(BpmDummieFactory.createPhase(id)));

		ResponseEntity<List<PhaseDto>> response = this.bpmController
				.availablePhase(String.format(BpmDummieFactory.CODE_FORMAT, id));

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		verify(this.bpmService, times(1)).availablePhases(anyString());

		List<PhaseDto> listaResponse = response.getBody();
		assertNotNull(listaResponse);
		assertFalse(listaResponse.isEmpty());
		assertTrue(listaResponse.size() == 1);
		assertEquals(String.format(BpmDummieFactory.DESCRIPCION_FORMAT, id),
				listaResponse.get(0).getDescription());
	}

	/** Test return a existing process. */
	@Test
	@DisplayName("Test return a existing process.")
	public void givenExistingProcessWhenFindThenReturnProcess() {
		long id = 1;
		when(this.bpmService.findByCode(any()))
				.thenReturn(BpmDummieFactory.createProcess(id));

		ResponseEntity<ProcesDto> response = this.bpmController
				.findByCode(String.format(BpmDummieFactory.CODE_FORMAT, id));

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		verify(this.bpmService, times(1)).findByCode(anyString());

		ProcesDto dto = response.getBody();
		assertNotNull(dto);
		assertNotNull(dto.getId());
		assertEquals(BpmDummieFactory.generateCode(id), dto.getCode());
	}

	/**
	 * Test return a existing process.
	 *
	 * @throws BpmException
	 *             Excepcion.
	 */
	@Test
	@DisplayName("Testing that phase change when next is executed.")
	public void givenProcessCreatedWhenNextPhaseThenReturnOK()
			throws BpmException {
		long id = 1;

		doNothing().when(this.bpmService).nextPhase(anyString(), anyString(),
				anyString());

		ResponseEntity<Void> response = this.bpmController.next(
				String.format(BpmDummieFactory.CODE_FORMAT, id),
				String.format(BpmDummieFactory.CODE_FORMAT, id));

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}

	/**
	 * Testing that phase change when 4xx is throw.
	 *
	 * @throws BpmException
	 *             Excepcion.
	 */
	@Test
	@DisplayName("Testing that phase change when 4xx is throw.")
	public void givenProcessCreatedWhenNextPhaseThenReturnError()
			throws BpmException {
		long id = 1;

		doThrow(new BpmException()).when(this.bpmService).nextPhase(any(), anyString(),
				anyString());

		ResponseEntity<Void> response = this.bpmController.next(
				String.format(BpmDummieFactory.CODE_FORMAT, id),
				String.format(BpmDummieFactory.CODE_FORMAT, id));

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.NO_CONTENT.equals(response.getStatusCode()));
	}
}
