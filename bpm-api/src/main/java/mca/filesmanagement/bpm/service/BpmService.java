package mca.filesmanagement.bpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.port.in.IProcesUseCase;

@Service
public class BpmService {

	@Autowired
	private IProcesUseCase procesUseCase;

	/** Constructor por defecto. */
	public BpmService() {
		super();
	}

	/**
	 * Pasa a la siguiente fase un proceso determinado.
	 * @param user Usuario que tramita el cambio de fase.
	 * @param processCode Identificador único del proceso.
	 * @param phase Fase hacía la que se pretende tramitar.
	 * @throws BpmException Excepción lanzada si la tramitación no puede tener lugar.
	 */
	public void nextPhase(String user, String processCode, String phase) throws BpmException {
		PhaseCodeEnum toPhase = PhaseCodeEnum.valueOf(phase);
		this.procesUseCase.nextPhase(user, processCode, toPhase);
	}

	/**
	 * Devuelve el DTO del proceso a partir de su código externo único.
	 * @param processCode identificador único del proceso a buscar.
	 * @return DTO del proceso encontrado.
	 */
	public ProcesDto findByCode(String processCode) {
		return this.procesUseCase.findByCode(processCode);
	}

	/**
	 * Devuelve las fases disponibles que tiene un proceso a partir de una en concreto.
	 * @param code Código único de la fase sobre la que se pregunta.
	 * @return Lista de fases disponibles a partir de la indicada.
	 */
	public List<PhaseDto> availablePhases(String code){
		PhaseCodeEnum phaseCode = PhaseCodeEnum.valueOf(code);
		return this.procesUseCase.availablePhases(phaseCode);
	}
}
