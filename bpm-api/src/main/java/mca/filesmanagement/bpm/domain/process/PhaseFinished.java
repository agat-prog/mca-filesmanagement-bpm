package mca.filesmanagement.bpm.domain.process;

import java.util.ArrayList;
import java.util.List;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Fase finalizada de un proceso.
 *
 * @author agat
 */
public class PhaseFinished extends Phase {

	/** Constructor por defecto. */
	public PhaseFinished() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isNavegableTo(PhaseCodeEnum toPhase) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isFinish() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PhaseCodeEnum> availablesPhase() {
		return new ArrayList<>(0);
	}
}
