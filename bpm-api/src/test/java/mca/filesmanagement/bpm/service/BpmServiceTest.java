package mca.filesmanagement.bpm.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.bpm.BpmDummieFactory;
import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.domain.usescases.ProcesUseCaseImpl;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Services tests")
public class BpmServiceTest {

	@InjectMocks
	private BpmService bpmService;

	@Mock
	private ProcesUseCaseImpl procesUseCase;

	/***/
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/** Test find a existing process by code. */
	@Test
	@DisplayName("Test find a existing process by code")
	public void givenAExistingProcessWhenFindThenReturnProcesDto() {
		long id = 1;

		when(this.procesUseCase.findByCode(any())).thenReturn(BpmDummieFactory.createProcess(id));

		ProcesDto procesDto = this.bpmService.findByCode(String.format(BpmDummieFactory.CODE_FORMAT, id));

		verify(this.procesUseCase, times(1)).findByCode(any());
		assertNotNull(procesDto);
		assertEquals(BpmDummieFactory.generateCode(id), procesDto.getCode());
	}

	/** Test list of available phases. */
	@Test
	@DisplayName("Test list of available phases.")
	public void givenAExistingCodeProcessWhenRequestAvailabePhaseThenReturnList() {
		long id = 1;

		when(this.procesUseCase.availablePhases(any())).thenReturn(Arrays.asList(BpmDummieFactory.createPhase(id)));

		List<PhaseDto> list = this.bpmService.availablePhases(PhaseCodeEnum.INICIAL.name());

		verify(this.procesUseCase, times(1)).availablePhases(any());
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals(String.format(BpmDummieFactory.DESCRIPCION_FORMAT, id), list.get(0).getDescription());
	}

	/**
	 * Test next phase.
	 *
	 * @throws BpmException
	 **/
	@Test
	@DisplayName("Test next phase")
	public void givenAExistingPhaseWhenNextThenReturnOk() throws BpmException {
		doNothing().when(this.procesUseCase).nextPhase(anyString(), anyString(), any());

		this.bpmService.nextPhase("user", "processCode", PhaseCodeEnum.INICIAL.name());

		verify(this.procesUseCase, times(1)).nextPhase(anyString(), anyString(), any());
	}
}
