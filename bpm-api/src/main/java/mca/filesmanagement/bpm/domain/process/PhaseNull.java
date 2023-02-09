package mca.filesmanagement.bpm.domain.process;

import java.util.ArrayList;
import java.util.List;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Fase neutra o nula.
 *
 * @author agat
 */
public class PhaseNull extends Phase {

	/***/
	public PhaseNull() {
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
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PhaseCodeEnum> availablesPhase() {
		return new ArrayList<>(0);
	}
}
