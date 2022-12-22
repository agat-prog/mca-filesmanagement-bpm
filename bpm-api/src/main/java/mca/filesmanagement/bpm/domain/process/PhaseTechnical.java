package mca.filesmanagement.bpm.domain.process;

import java.util.Arrays;
import java.util.List;

import mca.filesmanagement.bpm.commons.PHASE_CODE;

class PhaseTechnical extends Phase {

	public PhaseTechnical() {
		super();
	}
	
	@Override
	protected boolean isNavegableTo(PHASE_CODE toPhase) {
		return PHASE_CODE.RECHAZADO.equals(toPhase) 
				|| PHASE_CODE.VALIDADO.equals(toPhase);
	}

	@Override
	protected boolean isFinish() {
		return false;
	}

	@Override
	public List<PHASE_CODE> availablesPhase() {
		return Arrays.asList(PHASE_CODE.RECHAZADO, PHASE_CODE.VALIDADO);
	}
}
