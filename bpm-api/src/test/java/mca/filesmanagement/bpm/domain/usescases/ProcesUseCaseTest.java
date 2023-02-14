package mca.filesmanagement.bpm.domain.usescases;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import mca.filesmanagement.bpm.infraestructure.adapter.ProcesRepositoryAdapter;
import mca.filesmanagement.bpm.infraestructure.adapter.PublicationService;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Use cases tests")
public class ProcesUseCaseTest {

	@InjectMocks
	private ProcesUseCaseImpl procesUseCase;

	@Mock
	private ProcesRepositoryAdapter procesRepository;

	@Mock
	private PublicationService publisher;

	/***/
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/** Test proces creation. */
	@Test
	@DisplayName("Test proces creation")
	public void givenAUserWhenCreateProcessThenIdReturn() {
		long id = 1;
		String user = "user";

		when(this.procesRepository.createProces(any())).thenReturn(id);
		when(this.procesRepository.findById(anyLong())).thenReturn(BpmDummieFactory.createProcess(id));

		Long returnedId = this.procesUseCase.createProces(user);

		assertTrue(id == returnedId.longValue());
	}

	/** Test next phase.
	 *
	 * @throws BpmException
	 **/
	@Test
	@DisplayName("Test next phase")
	public void givenExistProcessWhenNextPhaseThenNextPhaseOccurred() throws BpmException {
		long id = 1;

		when(this.procesRepository.findByCode(anyString())).thenReturn(BpmDummieFactory.createProcess(id));
		when(this.procesRepository.findPhaseByCode(any())).thenReturn(BpmDummieFactory.createPhase(id));
		doNothing().when(this.procesRepository).save(any());

		String user = "user";
		PhaseCodeEnum toPhase = PhaseCodeEnum.ANALISIS_TECNICO;
		String processCode = BpmDummieFactory.generateCode(id);
		this.procesUseCase.nextPhase(user, processCode, toPhase);

		verify(this.procesRepository, times(1)).save(any());
	}

	/** Test find proces by code. */
	@Test
	@DisplayName("Test find proces by code")
	public void givenExistsProcessWhenFindeByCodeThenReturnProcesDto() {
		long id = 1;

		when(this.procesRepository.findByCode(anyString())).thenReturn(BpmDummieFactory.createProcess(id));

		ProcesDto proces = this.procesUseCase.findByCode(BpmDummieFactory.generateCode(id));

		assertNotNull(proces);
		assertNotNull(proces.getId());
		assertEquals(BpmDummieFactory.generateCode(id), proces.getCode());
	}

	/** Test list of available phases. */
	@Test
	@DisplayName("Test list of available phases.")
	public void givenAExistingCodeProcessWhenRequestAvailabePhaseThenReturnList() {
		long id = 1;

		when(this.procesRepository.findPhaseByCode(any())).thenReturn(BpmDummieFactory.createPhase(id));

		List<PhaseDto> list = this.procesUseCase.availablePhases(PhaseCodeEnum.INICIAL);

		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals(String.format(BpmDummieFactory.DESCRIPCION_FORMAT, id), list.get(0).getDescription());
	}

	/** Test find proces by id. */
	@Test
	@DisplayName("Test find proces by id.")
	public void givenAExistingProcessWhenFindeThenReturnProces() {
		long id = 1;

		when(this.procesRepository.findById(anyLong())).thenReturn(BpmDummieFactory.createProcess(id));

		ProcesDto proces = this.procesUseCase.findById(id);

		verify(this.procesRepository, times(1)).findById(anyLong());
		assertNotNull(proces);
		assertNotNull(proces.getId());
		assertEquals(BpmDummieFactory.generateCode(id), proces.getCode());
	}
}
