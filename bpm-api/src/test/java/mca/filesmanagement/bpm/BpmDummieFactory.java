package mca.filesmanagement.bpm;

import java.util.Date;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.domain.process.ParamCreatePhase;

/**
 * Factoría de objetos dummy para test.
 *
 * @author agat
 */
public final class BpmDummieFactory {

	public static final String CODE_FORMAT = "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
	public static final String DESCRIPCION_FORMAT = "descripcion_%s";
	public static final String USER_FORMAT = "user_%s";

	/** Constructor por defecto. */
	private BpmDummieFactory() {
		super();
	}

	/**
	 * Genera una instancia de PhaseDto rellena.
	 * @param id Identificador del DTO.
	 * @return PhaseDto.
	 */
	public static PhaseDto createPhase(long id) {
		PhaseDto dto = new PhaseDto();
		dto.setCode(PhaseCodeEnum.ANALISIS_TECNICO);
		dto.setDescription(String.format(DESCRIPCION_FORMAT, id));
		dto.setId(id);
		return dto;
	}

	/**
	 * Genera una instancia de ProcessDto rellena.
	 * @param id Identificador.
	 * @return ProcesDto.
	 */
	public static ProcesDto createProcess(long id) {
		ProcesDto dto = new ProcesDto();
		dto.setActive(false);
		dto.setCode(generateCode(id));
		dto.setDate(new Date());
		dto.setId(id);
		return dto;
	}

	/**
	 * Crea un parámetro necesario para la creación de fases.
	 * @param id Identificador PK.
	 * @return ParamCreatePhase.
	 */
	public static ParamCreatePhase createParamCreatePhase(long id) {
		ParamCreatePhase paramCreatePhase = new ParamCreatePhase(
				id,
				PhaseCodeEnum.INICIAL,
				String.format(DESCRIPCION_FORMAT, id),
				new Date(),
				null,
				String.format(USER_FORMAT, id),
				null);
		return paramCreatePhase;
	}

	/**
	 * Genera un UUID relleno de un mismo número.
	 * @param id ID.
	 * @return String
	 */
	public static String generateCode(long id) {
		return CODE_FORMAT.replaceAll("X", String.valueOf(id));
	}
}
