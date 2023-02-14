package mca.filesmanagement.bpm.infraestructure.adapter;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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
import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.PhaseInstanceDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.infraestructure.repository.JpaFhaseRepository;
import mca.filesmanagement.bpm.infraestructure.repository.JpaPhaseInstanceRepository;
import mca.filesmanagement.bpm.infraestructure.repository.JpaProcesRepository;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Repositories tests")
public class ProcesRepositoryAdapterTest {

	@InjectMocks
	private ProcesRepositoryAdapter procesRepositoryAdapter;

	@Mock
	private JpaFhaseRepository jpaFhaseRepository;

	@Mock
	private JpaProcesRepository jpaProcesRepository;

	@Mock
	private JpaPhaseInstanceRepository jpaPhaseInstanceRepository;

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

		when(this.jpaProcesRepository.save(any())).thenReturn(BpmDummieFactory.createProcesEntity(id));
		when(this.jpaFhaseRepository.findByCode(any())).thenReturn(BpmDummieFactory.createPhaseEntity(id));
		when(this.jpaPhaseInstanceRepository.save(any())).thenReturn(any());

		Long returnedId = this.procesRepositoryAdapter.createProces(user);

		assertTrue(id == returnedId.longValue());
		verify(this.jpaProcesRepository, times(1)).save(any());
		verify(this.jpaFhaseRepository, times(1)).findByCode(any());
		verify(this.jpaPhaseInstanceRepository, times(1)).save(any());
	}

	/** Test save proces. */
	@Test
	@DisplayName("Test save proces")
	public void givenAProcesWhenSaveThenUpdateProces() {
		long id = 1;

		when(this.jpaProcesRepository.getIdByCode(anyString())).thenReturn(id);
		when(this.jpaProcesRepository.getById(anyLong())).thenReturn(BpmDummieFactory.createProcesEntity(id));
		when(this.jpaPhaseInstanceRepository.getById(anyLong())).thenReturn(BpmDummieFactory.createPhaseInstanceEntity(id));
		when(this.jpaFhaseRepository.findByCode(any())).thenReturn(BpmDummieFactory.createPhaseEntity(id));
		when(this.jpaPhaseInstanceRepository.save(any())).thenReturn(any());

		ProcesDto procesDto = BpmDummieFactory.createProcess(id);
		PhaseInstanceDto pahseInstanceDto = BpmDummieFactory.createPhaseInstance(2);
		pahseInstanceDto.setId(null);
		procesDto.getPhases().add(pahseInstanceDto);
		this.procesRepositoryAdapter.save(procesDto);

		verify(this.jpaProcesRepository, times(1)).getIdByCode(anyString());
		verify(this.jpaProcesRepository, times(1)).getById(anyLong());
		verify(this.jpaPhaseInstanceRepository, times(1)).getById(anyLong());
	}

	/** Test find proces by code. */
	@Test
	@DisplayName("Test find proces by code")
	public void givenExistsProcessWhenFindeByCodeThenReturnProcesDto() {
		long id = 1;

		when(this.jpaProcesRepository.getIdByCode(anyString())).thenReturn(id);
		when(this.jpaProcesRepository.getById(anyLong())).thenReturn(BpmDummieFactory.createProcesEntity(id));
		when(this.jpaPhaseInstanceRepository.findPhasesByProcess(anyLong())).thenReturn(Arrays.asList(BpmDummieFactory.createPhaseInstanceEntity(id)));

		ProcesDto proces = this.procesRepositoryAdapter.findByCode(BpmDummieFactory.generateCode(id));

		assertNotNull(proces);
		assertNotNull(proces.getId());
		assertEquals(BpmDummieFactory.generateCode(id), proces.getCode());
	}

	/** Test find proces by id. */
	@Test
	@DisplayName("Test find proces by id")
	public void givenExistsProcessWhenFindeByIdThenReturnProcesDto() {
		long id = 1;

		when(this.jpaProcesRepository.getById(anyLong())).thenReturn(BpmDummieFactory.createProcesEntity(id));

		ProcesDto proces = this.procesRepositoryAdapter.findById(id);

		assertNotNull(proces);
		assertNotNull(proces.getId());
		assertEquals(BpmDummieFactory.generateCode(id), proces.getCode());
	}

	/** Test find phase by code. */
	@Test
	@DisplayName("Test find phase by code")
	public void givenExistsPhaseWhenFindByCodeThenReturnPhaseDto() {
		long id = 1;

		when(this.jpaFhaseRepository.findByCode(any())).thenReturn(BpmDummieFactory.createPhaseEntity(id));

		PhaseDto dto = this.procesRepositoryAdapter.findPhaseByCode(PhaseCodeEnum.INICIAL);

		assertNotNull(dto);
		assertNotNull(dto.getId());
		assertEquals(PhaseCodeEnum.INICIAL, dto.getCode());
	}

	/** Test delete proces. */
	@Test
	@DisplayName("Test delete proces")
	public void givenExistsProcesWhenDeleteThenReturnOk() {
		long id = 1;

		when(this.jpaProcesRepository.getIdByCode(anyString())).thenReturn(id);
		doNothing().when(this.jpaPhaseInstanceRepository).deleteByProcess(anyLong());
		doNothing().when(this.jpaProcesRepository).deleteById(anyLong());

		this.procesRepositoryAdapter.deleteByCode(BpmDummieFactory.generateCode(id));

		verify(this.jpaProcesRepository, times(1)).getIdByCode(anyString());
		verify(this.jpaPhaseInstanceRepository, times(1)).deleteByProcess(anyLong());
		verify(this.jpaProcesRepository, times(1)).deleteById(anyLong());
	}
}
