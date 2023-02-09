package mca.filesmanagement.bpm.domain;

import mca.filesmanagement.bpm.domain.process.ProcessAggregate;

/**
 * Contrato para las factorías de agregados de procesos.
 *
 * @author agat
 */
public interface IFactoryProcessAggregate {

	/**
	 * Crea un agregado de proceso.
	 * @return Agregado.
	 */
	ProcessAggregate create();
}
