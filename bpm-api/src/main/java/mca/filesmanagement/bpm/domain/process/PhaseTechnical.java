package mca.filesmanagement.bpm.domain.process;

import java.util.Arrays;
import java.util.List;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Fase de análisis técnico.
 *
 * @author agat
 */
public class PhaseTechnical extends Phase {

	/***/
	public PhaseTechnical() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isNavegableTo(PhaseCodeEnum toPhase) {
		return PhaseCodeEnum.RECHAZADO.equals(toPhase)
				|| PhaseCodeEnum.VALIDADO.equals(toPhase);
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
		return Arrays.asList(PhaseCodeEnum.RECHAZADO, PhaseCodeEnum.VALIDADO);
	}
}
