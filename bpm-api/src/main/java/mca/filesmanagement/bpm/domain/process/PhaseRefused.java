package mca.filesmanagement.bpm.domain.process;

import java.util.Arrays;
import java.util.List;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Fase rechazada de un proceso.
 *
 * @author agat
 */
public class PhaseRefused extends Phase {
	/***/
	public PhaseRefused() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isNavegableTo(PhaseCodeEnum toPhase) {
		return PhaseCodeEnum.INICIAL.equals(toPhase)
				|| PhaseCodeEnum.FINALIZADO.equals(toPhase);
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
		return Arrays.asList(PhaseCodeEnum.INICIAL, PhaseCodeEnum.FINALIZADO);
	}
}
