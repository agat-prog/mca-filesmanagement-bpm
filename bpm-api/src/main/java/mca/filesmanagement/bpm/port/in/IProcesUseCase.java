package mca.filesmanagement.bpm.port.in;

import java.util.List;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;

public interface IProcesUseCase {
	/**
	 * Crea un proceso de BPM.
	 * @param user Usuario que crea el proceso.
	 * @return Identificador único del proceso creado.
	 */
	Long createProces(String user);

	/**
	 * Encuentra el proceso cuyo id se pasa como argumento.
	 * @param id Identificador único del proceso.
	 * @return DTO del proceso encontrado.
	 */
	ProcesDto findById(long id);

	/**
	 * Elimina un proceso existente.
	 * @param code Código externo único del proceso a eliminar.
	 */
	void deleteByCode(String code);

	/**
	 * Pasa a la siguiente fase un proceso determinado.
	 * @param user Usuario que tramita el cambio de fase.
	 * @param processCode Identificador único del proceso.
	 * @param toPhase Fase hacía la que se pretende tramitar.
	 * @throws BpmException Excepción lanzada si la tramitación no puede tener lugar.
	 */
	void nextPhase(String user, String processCode, PhaseCodeEnum toPhase) throws BpmException;

	/**
	 * Devuelve el DTO del proceso a partir de su código externo único.
	 * @param processCode identificador único del proceso a buscar.
	 * @return DTO del proceso encontrado.
	 */
	ProcesDto findByCode(String processCode);

	/**
	 * Devuelve las fases disponibles que tiene un proceso a partir de una en concreto.
	 * @param phaseCode Código único de la fase sobre la que se pregunta.
	 * @return Lista de fases disponibles a partir de la indicada.
	 */
	List<PhaseDto> availablePhases(PhaseCodeEnum phaseCode);
}
