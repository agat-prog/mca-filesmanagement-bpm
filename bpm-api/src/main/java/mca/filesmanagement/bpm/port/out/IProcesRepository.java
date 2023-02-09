package mca.filesmanagement.bpm.port.out;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;

public interface IProcesRepository {

	/**
	 * Devuelve el DTO de un proceso encontrado a partir de su código externo.
	 * @param processCode Código externo único de proceso.
	 * @return DTO de proceso.
	 */
	ProcesDto findByCode(String processCode);

	/**
	 * Identificador de proceso creado por un usuario.
	 * @param user Username del usuario creador.
	 * @return Identificador único de proceso.
	 */
	Long createProces(String user);

	/**
	 * Devuelve el DTO de un proceso a partir de su PK.
	 * @param id Pk del proceso.
	 * @return DTO del proceso.
	 */
	ProcesDto findById(long id);

	/**
	 * Elimina un proceso a partir de su código externo.
	 * @param code Código externo.
	 */
	void deleteByCode(String code);

	/**
	 * Guarda un proceso en medio persistente.
	 * @param procesDto DTO del proceso.
	 */
	void save(ProcesDto procesDto);

	/**
	 * Devuelve el DTO de una fase a partir de su código externo.
	 * @param phaseCode Código externo de la fase.
	 * @return DTO de fase.
	 */
	PhaseDto findPhaseByCode(PhaseCodeEnum phaseCode);
}
