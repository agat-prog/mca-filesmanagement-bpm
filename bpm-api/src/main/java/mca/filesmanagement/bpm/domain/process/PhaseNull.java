package mca.filesmanagement.bpm.domain.process;

import java.util.ArrayList;
import java.util.List;

import mca.filesmanagement.bpm.commons.PHASE_CODE;

class PhaseNull extends Phase {

	public PhaseNull() {
		super();
	}
	
	@Override
	protected boolean isNavegableTo(PHASE_CODE toPhase) {
		return false;
	}

	@Override
	protected boolean isFinish() {
		return false;
	}
	
	@Override
	public List<PHASE_CODE> availablesPhase() {
		return new ArrayList<>(0);
	}
}
