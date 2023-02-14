package mca.filesmanagement.bpm.domain.process;

import java.util.Arrays;
import java.util.List;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Fase de validación de un proceso.
 *
 * @author agat
 */
public class PhaseValidated extends Phase {
	/***/
	public PhaseValidated() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isNavegableTo(PhaseCodeEnum toPhase) {
		return PhaseCodeEnum.FINALIZADO.equals(toPhase);
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
		return Arrays.asList(PhaseCodeEnum.FINALIZADO);
	}
}
