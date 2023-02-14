package mca.filesmanagement.bpm.port.out;

import bpm.api.messaging.events.ProcessDomainEvent;
import mca.filesmanagement.bpm.domain.process.ProcessAggregate;

public interface IPublicationService {

	/**
	 * Genera un evento de un agregado.
	 * @param aggregate Agregado del proceso.
	 * @param event Evento a procesar.
	 */
	void notify(ProcessAggregate aggregate, ProcessDomainEvent event);
}
