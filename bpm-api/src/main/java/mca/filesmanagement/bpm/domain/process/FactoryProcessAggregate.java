package mca.filesmanagement.bpm.domain.process;

import java.util.UUID;

import mca.filesmanagement.bpm.domain.IFactoryProcessAggregate;

public class FactoryProcessAggregate implements IFactoryProcessAggregate {

	public FactoryProcessAggregate() {
		super();
	}
	
	@Override
	public ProcessAggregate create() {
		String uuid = UUID.randomUUID().toString();
		ProcessAggregate fileAggregate = new ProcessAggregate(uuid);
		return fileAggregate;
	}
}
