package mca.filesmanagement.bpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PHASE_CODE;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.port.in.IProcesUseCase;

@Service
public class BpmService {

	@Autowired
	private IProcesUseCase procesUseCase;
	
	public BpmService() {
		super();
	}
	
	public void nextPhase(String user, String processCode, String phase) throws BpmException {
		PHASE_CODE toPhase = PHASE_CODE.valueOf(phase);
		this.procesUseCase.nextPhase(user, processCode, toPhase);
	}
	
	public ProcesDto findByCode(String processCode) {
		return this.procesUseCase.findByCode(processCode);
	}
	
	public List<PhaseDto> availablePhases(String code){
		PHASE_CODE phaseCode = PHASE_CODE.valueOf(code);
		return this.procesUseCase.availablePhases(phaseCode);
	}
}
