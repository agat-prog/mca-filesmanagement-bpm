package mca.filesmanagement.bpm;

import java.util.Date;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.PhaseInstanceDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.domain.process.ParamCreatePhase;
import mca.filesmanagement.bpm.infraestructure.model.PhaseEntity;
import mca.filesmanagement.bpm.infraestructure.model.PhaseInstanceEntity;
import mca.filesmanagement.bpm.infraestructure.model.ProcesEntity;

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
		dto.addPhase(createPhaseInstance(id));
		return dto;
	}

	/**
	 * Crea una instancia de fase.
	 * @param id Identificador
	 * @return PhaseInstanceDto.
	 */
	public static PhaseInstanceDto createPhaseInstance(long id) {
		PhaseInstanceDto dto = new PhaseInstanceDto();
		dto.setDate(new Date());
		dto.setId(id);
		dto.setUser(String.format(USER_FORMAT, id));
		dto.setPhaseCode(PhaseCodeEnum.INICIAL);
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
	 * Crea una entidad de JPA de un proceso.
	 * @param id Identificador.
	 * @return ProcesEntity.
	 */
	public static ProcesEntity createProcesEntity(long id) {
		ProcesEntity procesEntity = new ProcesEntity();
		procesEntity.setId(id);
		procesEntity.setActive(true);
		procesEntity.setCode(generateCode(id));
		procesEntity.setDate(new Date());
		return procesEntity;
	}

	/**
	 * Crea una entidad de JPA de una fase.
	 * @param id Identificador.
	 * @return PhaseEntity.
	 */
	public static PhaseEntity createPhaseEntity(long id) {
		PhaseEntity entity = new PhaseEntity();
		entity.setCode(PhaseCodeEnum.INICIAL);
		entity.setDescription(String.format(DESCRIPCION_FORMAT, id));
		entity.setId(id);
		return entity;
	}

	/**
	 * Crea una entidad de JPA de una instancia de fase.
	 * @param id Identificador.
	 * @return PhaseInstanceEntity.
	 */
	public static PhaseInstanceEntity createPhaseInstanceEntity(long id) {
		PhaseInstanceEntity entity = new PhaseInstanceEntity();
		entity.setDate(new Date());
		entity.setId(id);
		entity.setPhase(createPhaseEntity(id));
		entity.setProces(createProcesEntity(id));
		entity.setUser(String.format(USER_FORMAT, id));
		return entity;
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
