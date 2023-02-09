package mca.filesmanagement.bpm.domain.process;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.bpm.BpmDummieFactory;
import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.port.out.IProcesRepository;
import mca.filesmanagement.bpm.port.out.IPublicationService;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Process Aggregate Test")
public class ProcessAggregateTest {

	private ProcessAggregate fileAggregate;

	@Mock
	private IPublicationService publisher;

	@Mock
	private IProcesRepository procesRepository;

	/** Inicialización de mocks. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.fileAggregate = this.init();
	}

	/** Test the process has been created ok. */
	@Test
	@DisplayName("Test the process has been created ok")
	public void givenANewProcessWhenRequestPhasesThenPhaseDefaultReturn() throws BpmException  {
		long id = 1;
		when(this.procesRepository.findPhaseByCode(any())).thenReturn(BpmDummieFactory.createPhase(id));
		doNothing().when(this.publisher).notify(any(), any());

		assertTrue(PhaseInitial.class.isInstance(this.fileAggregate.getActualPhase()));
		assertTrue(this.fileAggregate.getPhasesChanged().isEmpty());
	}

	/** Test next to a valid phase. */
	@Test
	@DisplayName("Test next to a valid phase")
	public void givenANewProcessWhenNextPhaseThenNewPhaseCreated() throws BpmException  {
		long id = 1;
		when(this.procesRepository.findPhaseByCode(any())).thenReturn(BpmDummieFactory.createPhase(id));
		doNothing().when(this.publisher).notify(any(), any());

		String user = "user";
		this.fileAggregate.next(user, PhaseCodeEnum.ANALISIS_TECNICO);

		assertTrue(PhaseTechnical.class.isInstance(this.fileAggregate.getActualPhase()));
		assertTrue(!this.fileAggregate.getPhasesChanged().isEmpty());
	}

	/** Test next to a not valid phase. */
	@Test
	@DisplayName("Test next to a not valid phase")
	public void givenANewProcessWhenNextNotValidPhaseThenExceptionThrown() {
		long id = 1;
		when(this.procesRepository.findPhaseByCode(any())).thenReturn(BpmDummieFactory.createPhase(id));
		doNothing().when(this.publisher).notify(any(), any());

		final String user = "user";
		assertThrows(BpmException.class,
				() -> this.fileAggregate.next(user, PhaseCodeEnum.RECHAZADO));
	}

	/** Test process finished ok. */
	@Test
	@DisplayName("Test process finished ok")
	public void givenANewProcessWhenAllPhasesThenFinalished() throws BpmException  {
		long id = 1;
		when(this.procesRepository.findPhaseByCode(any())).thenReturn(BpmDummieFactory.createPhase(id));
		doNothing().when(this.publisher).notify(any(), any());

		String user = "user";
		this.fileAggregate.next(user, PhaseCodeEnum.ANALISIS_TECNICO);
		assertTrue(this.fileAggregate.isActive());

		this.fileAggregate.next(user, PhaseCodeEnum.RECHAZADO);
		assertTrue(this.fileAggregate.isActive());

		this.fileAggregate.next(user, PhaseCodeEnum.INICIAL);
		assertTrue(this.fileAggregate.isActive());

		this.fileAggregate.next(user, PhaseCodeEnum.ANALISIS_TECNICO);
		assertTrue(this.fileAggregate.isActive());

		this.fileAggregate.next(user, PhaseCodeEnum.VALIDADO);
		assertTrue(this.fileAggregate.isActive());

		this.fileAggregate.next(user, PhaseCodeEnum.FINALIZADO);

		assertTrue(PhaseFinished.class.isInstance(this.fileAggregate.getActualPhase()));
		assertTrue(!this.fileAggregate.getPhasesChanged().isEmpty());
		assertFalse(this.fileAggregate.isActive());
		assertNotNull(this.fileAggregate.getDate());
		assertNotNull(this.fileAggregate.getActualPhase().getDate());
		assertNotNull(this.fileAggregate.getActualPhase().getDateFinished());
		assertNotNull(this.fileAggregate.getActualPhase().getUserFinished());
	}

	/** Test availables phases are returned. */
	@Test
	@DisplayName("Test availables phases are returned")
	public void givenAPhaseWhenRequestAvailableThenPhasesReturn()  {
		Phase phase = new PhaseInitial();

		assertEquals(phase.availablesPhase().size(), 1);
		assertTrue(phase.availablesPhase().contains(PhaseCodeEnum.ANALISIS_TECNICO));
		assertTrue(phase.isNavegableTo(PhaseCodeEnum.ANALISIS_TECNICO));
		assertFalse(phase.isNavegableTo(PhaseCodeEnum.INICIAL));

		phase = new PhaseTechnical();
		assertEquals(phase.availablesPhase().size(), 2);
		assertTrue(phase.availablesPhase().contains(PhaseCodeEnum.RECHAZADO));
		assertTrue(phase.availablesPhase().contains(PhaseCodeEnum.VALIDADO));
		assertTrue(phase.isNavegableTo(PhaseCodeEnum.RECHAZADO));
		assertTrue(phase.isNavegableTo(PhaseCodeEnum.VALIDADO));
		assertFalse(phase.isNavegableTo(PhaseCodeEnum.ANALISIS_TECNICO));

		phase = new PhaseRefused();
		assertEquals(phase.availablesPhase().size(), 2);
		assertTrue(phase.availablesPhase().contains(PhaseCodeEnum.INICIAL));
		assertTrue(phase.availablesPhase().contains(PhaseCodeEnum.FINALIZADO));
		assertTrue(phase.isNavegableTo(PhaseCodeEnum.INICIAL));
		assertTrue(phase.isNavegableTo(PhaseCodeEnum.FINALIZADO));
		assertFalse(phase.isNavegableTo(PhaseCodeEnum.ANALISIS_TECNICO));

		phase = new PhaseValidated();
		assertEquals(phase.availablesPhase().size(), 1);
		assertTrue(phase.availablesPhase().contains(PhaseCodeEnum.FINALIZADO));
		assertTrue(phase.isNavegableTo(PhaseCodeEnum.FINALIZADO));
		assertFalse(phase.isNavegableTo(PhaseCodeEnum.ANALISIS_TECNICO));

		phase = new PhaseFinished();
		assertTrue(phase.availablesPhase().isEmpty());
		assertFalse(phase.isNavegableTo(PhaseCodeEnum.ANALISIS_TECNICO));

		phase = new PhaseNull();
		assertTrue(phase.availablesPhase().isEmpty());
		assertFalse(phase.isNavegableTo(PhaseCodeEnum.ANALISIS_TECNICO));
		assertFalse(phase.isFinish());
	}

	private ProcessAggregate init() {
		long id = 1;
		ProcessAggregate fileAggregate = new FactoryProcessAggregate().create();
		fileAggregate.setProcesRepository(this.procesRepository);
		fileAggregate.setPublisher(this.publisher);
		fileAggregate.addPhase(BpmDummieFactory.createParamCreatePhase(id));
		return fileAggregate;
	}
}
