package mca.filesmanagement.bpm.domain.usescases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PHASE_CODE;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.PhaseInstanceDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.domain.FactoryProvider;
import mca.filesmanagement.bpm.domain.process.FactoryPhase;
import mca.filesmanagement.bpm.domain.process.ParamCreatePhase;
import mca.filesmanagement.bpm.domain.process.Phase;
import mca.filesmanagement.bpm.domain.process.ProcessAggregate;
import mca.filesmanagement.bpm.port.in.IProcesUseCase;
import mca.filesmanagement.bpm.port.out.IProcesRepository;
import mca.filesmanagement.bpm.port.out.IPublicationService;

@Service
public class ProcesUseCaseImpl implements IProcesUseCase {
	
	@Autowired
	private IProcesRepository procesRepository;
	
	@Autowired
	private IPublicationService publisher;
	
	public ProcesUseCaseImpl() {
		super();
	}

	@Override
	public Long createProces(String user) {
		long id = this.procesRepository.createProces(user);
		ProcesDto procesDto = this.procesRepository.findById(id);
		ProcessAggregate processAgg = load(procesDto);
		processAgg.afterCreated();
		return id;	
	}
	
	@Override
	public void nextPhase(String user, String processCode, PHASE_CODE toPhase) throws BpmException {
		ProcesDto procesDto = this.procesRepository.findByCode(processCode);
		ProcessAggregate processAgg = load(procesDto);
		processAgg.next(user, toPhase);
		
		this.procesRepository.save(toDto(processAgg));
	}
	
	@Override
	public ProcesDto findByCode(String processCode) {
		return this.procesRepository.findByCode(processCode);
	}
	
	@Override
	public List<PhaseDto> availablePhases(PHASE_CODE phaseCode){
		List<PhaseDto> phases = new ArrayList<>();
		Phase phase = FactoryPhase.create(phaseCode);
		phase.availablesPhase().forEach(code -> phases.add(this.procesRepository.findByCode(code)));
		return phases;
	}
	
	private ProcesDto toDto(ProcessAggregate processAgg) {
		ProcesDto procesDto = new ProcesDto();
		procesDto.setActive(processAgg.isActive());
		procesDto.setCode(processAgg.getId());
		procesDto.setDate(processAgg.getDate());
		processAgg.getPhasesChanged().stream()
					.map(this::toDto)
					.toList()
					.forEach(phase -> procesDto.addPhase(phase));
					;
		return procesDto;
	}
	
	private PhaseInstanceDto toDto(Phase phase) {
		PhaseInstanceDto phaseDto = new PhaseInstanceDto();
		phaseDto.setDate(phase.getDate());
		phaseDto.setDateFinished(phase.getDateFinished());
		phaseDto.setId(phase.getId());
		phaseDto.setPhaseCode(phase.getCode());
		phaseDto.setPhaseDescription(phase.getDescription());
		phaseDto.setUser(phase.getUser());
		phaseDto.setUserFinished(phase.getUserFinished());
		return phaseDto;
	}
	
	private ProcessAggregate load(ProcesDto procesDto) {
		final ProcessAggregate processAgg = FactoryProvider.getFactoryProcessAggregate().create();
		processAgg.setId(procesDto.getCode());
		procesDto.getPhases().forEach(phaseDto -> {
			ParamCreatePhase paramCreatePhase = new ParamCreatePhase(phaseDto.getId(), 
					phaseDto.getPhaseCode(),
					phaseDto.getPhaseDescription(),
					phaseDto.getDate(), 
					phaseDto.getDateFinished(), 
					phaseDto.getUser(), 
					phaseDto.getUserFinished());
			processAgg.addPhase(paramCreatePhase);
		});
		processAgg.setPublisher(this.publisher);
		processAgg.setProcesRepository(this.procesRepository);
		return processAgg;
	}
	
	@Override
	public ProcesDto findById(long id) {
		return this.procesRepository.findById(id);
	}
	
	@Override
	public void deleteByCode(String code) {
		this.procesRepository.deleteByCode(code);
	}
}
