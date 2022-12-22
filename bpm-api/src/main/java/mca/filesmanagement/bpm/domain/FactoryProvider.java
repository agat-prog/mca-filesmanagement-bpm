package mca.filesmanagement.bpm.domain;

import mca.filesmanagement.bpm.domain.process.FactoryProcessAggregate;

public class FactoryProvider {

	private FactoryProvider() {
		super();
	}
	
	public static IFactoryProcessAggregate getFactoryProcessAggregate() {
		return new FactoryProcessAggregate();
	}
}
