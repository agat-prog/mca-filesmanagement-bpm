package mca.filesmanagement.bpm.domain.process;

import java.util.Arrays;
import java.util.List;

import mca.filesmanagement.bpm.commons.PHASE_CODE;

class PhaseRefused extends Phase {

	public PhaseRefused() {
		super();
	}
	
	@Override
	protected boolean isNavegableTo(PHASE_CODE toPhase) {
		return PHASE_CODE.INICIAL.equals(toPhase) 
				|| PHASE_CODE.FINALIZADO.equals(toPhase);
	}

	@Override
	protected boolean isFinish() {
		return false;
	}
	
	@Override
	public List<PHASE_CODE> availablesPhase() {
		return Arrays.asList(PHASE_CODE.INICIAL, PHASE_CODE.FINALIZADO);
	}
}
