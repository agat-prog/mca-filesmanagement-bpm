package mca.filesmanagement.bpm.infraestructure.adapter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import bpm.api.messaging.events.ProcessDomainEvent;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import mca.filesmanagement.bpm.domain.process.ProcessAggregate;
import mca.filesmanagement.bpm.port.out.IPublicationService;

@Service
public class PublicationService extends AbstractAggregateDomainEventPublisher<ProcessAggregate, ProcessDomainEvent> implements IPublicationService {

	private static Logger LOG = LoggerFactory.getLogger(PublicationService.class);
	
	public PublicationService(DomainEventPublisher eventPublisher) {
		super(eventPublisher, ProcessAggregate.class, ProcessAggregate::getId);
	}
	
	@Override
	public void notify(ProcessAggregate aggregate, ProcessDomainEvent event) {
		LOG.info("NotificationService.notify: "+ aggregate.getId());
		this.publish(aggregate, Arrays.asList(event));
	}
}
