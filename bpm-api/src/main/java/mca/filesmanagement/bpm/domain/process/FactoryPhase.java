package mca.filesmanagement.bpm.domain.process;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mca.filesmanagement.bpm.commons.PHASE_CODE;

public class FactoryPhase {

	private static Map<PHASE_CODE, Class<?>> mapa = new HashMap<>();
	
	static {
		mapa.put(PHASE_CODE.INICIAL, PhaseInitial.class);
		mapa.put(PHASE_CODE.FINALIZADO, PhaseFinished.class);
		mapa.put(PHASE_CODE.ANALISIS_TECNICO, PhaseTechnical.class);
		mapa.put(PHASE_CODE.RECHAZADO, PhaseRefused.class);
		mapa.put(PHASE_CODE.VALIDADO, PhaseValidated.class);
		mapa.put(PHASE_CODE.NULL, PhaseNull.class);
	}
	
	public static Phase create(PHASE_CODE phaseCode) {
		Constructor<?> ctor;
		try {
			ctor = mapa.getOrDefault(phaseCode, PhaseNull.class).getConstructor();
			Phase phase = (Phase) ctor.newInstance();
			phase.setCode(phaseCode);
			phase.setDate(new Date());
			return phase;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new PhaseNull();
	}
}

