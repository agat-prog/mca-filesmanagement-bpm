package mca.filesmanagement.bpm.port.in;

import java.util.List;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PHASE_CODE;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;

public interface IProcesUseCase {

	Long createProces(String user);
	ProcesDto findById(long id);
	void deleteByCode(String code);
	void nextPhase(String user, String processCode, PHASE_CODE toPhase) throws BpmException;
	ProcesDto findByCode(String processCode);
	List<PhaseDto> availablePhases(PHASE_CODE phaseCode);
}
