package mca.filesmanagement.bpm.domain.process;

import java.util.UUID;

import mca.filesmanagement.bpm.domain.IFactoryProcessAggregate;

/**
 * Factoría que crea agregados de procesos.
 *
 * @author agat
 */
public class FactoryProcessAggregate implements IFactoryProcessAggregate {

	/** Constructor por defecto. */
	public FactoryProcessAggregate() {
		super();
	}

	/**
	 * Creaa un agregado de procesos.
	 * @return Agregado.
	 */
	@Override
	public ProcessAggregate create() {
		String uuid = UUID.randomUUID().toString();
		ProcessAggregate fileAggregate = new ProcessAggregate(uuid);
		return fileAggregate;
	}
}
