package mca.filesmanagement.bpm.domain;

import mca.filesmanagement.bpm.domain.process.FactoryProcessAggregate;

/**
 * Factoría que devuelve, a su vez, la factoría que crea agregados de procesos.
 *
 * @author agat
 */
public final class FactoryProvider {

	private FactoryProvider() {
		super();
	}

	/**
	 * Devuelve una factoría encargada de crear agregados de procesos.
	 * @return Factoría.
	 */
	public static IFactoryProcessAggregate getFactoryProcessAggregate() {
		return new FactoryProcessAggregate();
	}
}
