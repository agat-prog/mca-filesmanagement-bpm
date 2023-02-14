package mca.filesmanagement.bpm.domain.process;

import java.util.Arrays;
import java.util.List;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Fase inicial de un proceso.
 *
 * @author agat
 */
public class PhaseInitial extends Phase {

	/** Constructor por defecto. */
	public PhaseInitial() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isNavegableTo(PhaseCodeEnum toPhase) {
		return PhaseCodeEnum.ANALISIS_TECNICO.equals(toPhase);
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
		return Arrays.asList(PhaseCodeEnum.ANALISIS_TECNICO);
	}
}
