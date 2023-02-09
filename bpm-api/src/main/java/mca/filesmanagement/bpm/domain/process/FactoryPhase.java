package mca.filesmanagement.bpm.domain.process;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Factoría que crea fases a partir de sus código externos.
 *
 * @author agat
 */
public final class FactoryPhase {

	private static Map<PhaseCodeEnum, Class<?>> mapa = new HashMap<>();

	static {
		mapa.put(PhaseCodeEnum.INICIAL, PhaseInitial.class);
		mapa.put(PhaseCodeEnum.FINALIZADO, PhaseFinished.class);
		mapa.put(PhaseCodeEnum.ANALISIS_TECNICO, PhaseTechnical.class);
		mapa.put(PhaseCodeEnum.RECHAZADO, PhaseRefused.class);
		mapa.put(PhaseCodeEnum.VALIDADO, PhaseValidated.class);
		mapa.put(PhaseCodeEnum.NULL, PhaseNull.class);
	}

	/** Constructor por defecto de la factoría. */
	private FactoryPhase() {
		super();
	}

	/**
	 * Crea una fase según el código de fase.
	 * @param phaseCode
	 * @return La fase creada.
	 */
	public static Phase create(PhaseCodeEnum phaseCode) {
		Constructor<?> ctor;
		try {
			ctor = mapa.getOrDefault(phaseCode, PhaseNull.class).getConstructor();
			Phase phase = (Phase) ctor.newInstance();
			phase.setCode(phaseCode);
			phase.setDate(new Date());
			return phase;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PhaseNull();
	}
}

