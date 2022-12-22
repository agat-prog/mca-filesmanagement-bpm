package mca.filesmanagement.bpm.infraestructure.adapter;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.bpm.commons.PHASE_CODE;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.PhaseInstanceDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.infraestructure.model.PhaseEntity;
import mca.filesmanagement.bpm.infraestructure.model.PhaseInstanceEntity;
import mca.filesmanagement.bpm.infraestructure.model.ProcesEntity;
import mca.filesmanagement.bpm.infraestructure.repository.JpaFhaseRepository;
import mca.filesmanagement.bpm.infraestructure.repository.JpaPhaseInstanceRepository;
import mca.filesmanagement.bpm.infraestructure.repository.JpaProcesRepository;
import mca.filesmanagement.bpm.port.out.IProcesRepository;

@Service
public class ProcesRepositoryAdapter implements IProcesRepository {

	@Autowired
	private JpaFhaseRepository jpaFhaseRepository;
	
	@Autowired
	private JpaProcesRepository jpaProcesRepository;
	
	@Autowired
	private JpaPhaseInstanceRepository jpaPhaseInstanceRepository;

	public ProcesRepositoryAdapter() {
		super();
	}

	@Override
	@Transactional
	public Long createProces(String user) {
		String code = UUID.randomUUID().toString();
		ProcesEntity procesEntity = new ProcesEntity();
		procesEntity.setActive(true);
		procesEntity.setCode(code);
		procesEntity.setDate(new Date());
		
		procesEntity = this.jpaProcesRepository.save(procesEntity);
		
		PhaseInstanceEntity phaseInstance = new PhaseInstanceEntity();
		phaseInstance.setProces(procesEntity);
		phaseInstance.setDate(new Date());
		phaseInstance.setPhase(this.jpaFhaseRepository.findByCode(PHASE_CODE.INICIAL));
		phaseInstance.setUser(user);

		this.jpaPhaseInstanceRepository.save(phaseInstance);
		
		return procesEntity.getId();
	}

	@Override
	@Transactional
	public void save(ProcesDto procesDto) {
		long idProces = this.jpaProcesRepository.getIdByCode(procesDto.getCode());
		ProcesEntity procesEntity = this.jpaProcesRepository.getById(idProces);
		procesEntity.setActive(procesDto.isActive());
		procesEntity.setDate(procesDto.getDate());
	
		// Se actualizan las fases ya existentes
		procesDto.getPhases().stream()
							.filter(phase -> Objects.nonNull(phase.getId()))
							.forEach(phase -> {
								PhaseInstanceEntity phaseInstance = this.jpaPhaseInstanceRepository.getById(phase.getId());
								phaseInstance.setDateFinished(phase.getDateFinished());
								phaseInstance.setUserFinished(phase.getUserFinished());
								this.jpaPhaseInstanceRepository.save(phaseInstance);
							});
							
		// Se guardan las fases nuevas
		procesDto.getPhases().stream()
						.filter(phase -> Objects.isNull(phase.getId()))
						.forEach(phase -> {
							PhaseInstanceEntity phaseInstance = new PhaseInstanceEntity();
							phaseInstance.setProces(procesEntity);
							phaseInstance.setDate(phase.getDate());
							phaseInstance.setPhase(this.jpaFhaseRepository.findByCode(phase.getPhaseCode()));
							phaseInstance.setUser(phase.getUser());
							phaseInstance.setUserFinished(phase.getUserFinished());
							phaseInstance.setDateFinished(phase.getDateFinished());
							this.jpaPhaseInstanceRepository.save(phaseInstance);
						});
	}
	
	@Override
	@Transactional
	public ProcesDto findById(long id) {
		return this.toDto(this.jpaProcesRepository.getById(id));
	}
	
	@Override
	@Transactional
	public ProcesDto findByCode(String processCode) {
		long idProces = this.jpaProcesRepository.getIdByCode(processCode);
		return this.toDto(this.jpaProcesRepository.getById(idProces));
	}
	
	@Override
	@Transactional
	public PhaseDto findByCode(PHASE_CODE phaseCode) {
		PhaseEntity entity = this.jpaFhaseRepository.findByCode(phaseCode);
		PhaseDto dto = new PhaseDto();
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		dto.setId(entity.getId());
		return dto;
	}

	@Override
	@Transactional
	public void deleteByCode(String code) {
		long idProces = this.jpaProcesRepository.getIdByCode(code);
		this.jpaPhaseInstanceRepository.deleteByProcess(idProces);
		this.jpaProcesRepository.deleteById(idProces);
	}
	
	private ProcesDto toDto(ProcesEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		
		final ProcesDto dto = new ProcesDto();
		dto.setActive(entity.isActive());
		dto.setCode(entity.getCode());
		dto.setDate(entity.getDate());
		dto.setId(entity.getId());
		
		this.jpaPhaseInstanceRepository.findPhasesByProcess(entity.getId())
						.stream()
						.map(this::toDto)
						.toList()
						.forEach(p -> dto.addPhase(p));
						;
		return dto;
	}
	
	private PhaseInstanceDto toDto(PhaseInstanceEntity phaseEntity) {
		PhaseInstanceDto phaseDto = new PhaseInstanceDto();
		phaseDto.setDate(phaseEntity.getDate());
		phaseDto.setDateFinished(phaseEntity.getDateFinished());
		phaseDto.setId(phaseEntity.getId());
		phaseDto.setPhaseCode(phaseEntity.getPhaseCode());
		phaseDto.setPhaseDescription(phaseEntity.getPhaseDescription());
		phaseDto.setUser(phaseEntity.getUser());
		phaseDto.setUserFinished(phaseEntity.getUserFinished());
		return phaseDto;
	}
}
