package mca.filesmanagement.bpm.domain;

import mca.filesmanagement.bpm.domain.process.ProcessAggregate;

public interface IFactoryProcessAggregate {

	ProcessAggregate create();
}
