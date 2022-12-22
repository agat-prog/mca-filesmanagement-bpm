package mca.filesmanagement.bpm.port.out;

import bpm.api.messaging.events.ProcessDomainEvent;
import mca.filesmanagement.bpm.domain.process.ProcessAggregate;

public interface IPublicationService {
	void notify(ProcessAggregate aggregate, ProcessDomainEvent event);
}
