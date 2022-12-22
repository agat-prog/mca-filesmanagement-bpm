package mca.filesmanagement.bpm.domain.process;

import java.util.Arrays;
import java.util.List;

import mca.filesmanagement.bpm.commons.PHASE_CODE;

class PhaseInitial extends Phase {
	
	public PhaseInitial() {
		super();
	}
	
	@Override
	protected boolean isNavegableTo(PHASE_CODE toPhase) {
		return PHASE_CODE.ANALISIS_TECNICO.equals(toPhase);
	}

	@Override
	protected boolean isFinish() {
		return false;
	}
	
	@Override
	public List<PHASE_CODE> availablesPhase() {
		return Arrays.asList(PHASE_CODE.ANALISIS_TECNICO);
	}
}
