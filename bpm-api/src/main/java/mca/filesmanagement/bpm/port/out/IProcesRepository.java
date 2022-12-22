package mca.filesmanagement.bpm.port.out;

import mca.filesmanagement.bpm.commons.PHASE_CODE;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;

public interface IProcesRepository {

	ProcesDto findByCode(String processCode);
	Long createProces(String user); 
	ProcesDto findById(long id);
	void deleteByCode(String code);
	void save(ProcesDto procesDto);
	PhaseDto findByCode(PHASE_CODE phaseCode);
}
