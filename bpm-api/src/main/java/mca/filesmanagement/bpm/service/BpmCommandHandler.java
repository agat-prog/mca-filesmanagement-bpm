package mca.filesmanagement.bpm.service;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import java.util.UUID;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bpm.api.messaging.commands.BpmChannels;
import bpm.api.messaging.commands.CreateBpmCommand;
import bpm.api.messaging.commands.DeleteBpmCommand;
import bpm.api.messaging.replies.BpmCreatedEvent;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.port.in.IProcesUseCase;

public class BpmCommandHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(BpmCommandHandler.class);

	private IProcesUseCase procesUseCase;

	/**
	 * Crea un commandHandler para la SAGA de creación.
	 * @param procesUseCase Servicio para casos de usos de procesos.
	 */
	public BpmCommandHandler(IProcesUseCase procesUseCase) {
		super();

		this.procesUseCase = procesUseCase;
	}

	/**
	 * Devuelve un commandHandler configurado para la creación de la SAGA.
	 * @return commandHandler.
	 */
	public CommandHandlers commandHandlerDefinitions() {
		return SagaCommandHandlersBuilder.fromChannel(BpmChannels.CHANNEL_BPM_SERVICE)
				.onMessage(CreateBpmCommand.class, this::createBpm)
				.onMessage(DeleteBpmCommand.class, this::deleteBpm)
				.build();
	}

	/**
	 * Crea un proceso BPM a partir de un evento proveniente de una cola.
	 * @param cmd Comando que contiene el evento recibido.
	 * @return Mensaje a devolver: Success o Failure en caso de error.
	 */
	public Message createBpm(CommandMessage<CreateBpmCommand> cmd) {
		try {
			CreateBpmCommand command = cmd.getCommand();
			LOGGER.info(String.format("createBpm user -->  %s", command.getUser()));
			System.out.println("createBpm ... EUREKA");

			Long id = this.procesUseCase.createProces(command.getUser());
			ProcesDto proces = this.procesUseCase.findById(id);

			Assert.assertFalse(proces.getPhases().isEmpty());

			BpmCreatedEvent event = new BpmCreatedEvent();
			event.setPhase(proces.getPhases().get(0).getPhaseCode().name());
			event.setUuid(UUID.fromString(proces.getCode()));
			return withSuccess(event);
		} catch (Exception e) {
			return withFailure();
		}
	}

	/**
	 * Elimina un proceso BPM a partir de un evento proveniente de una cola.
	 * @param cmd Comando que contiene el evento recibido.
	 * @return Mensaje a devolver: Success o Failure en caso de error.
	 */
	public Message deleteBpm(CommandMessage<DeleteBpmCommand> cmd) {
		try {
			DeleteBpmCommand command = cmd.getCommand();

			LOGGER.info("Eliminando bpm: " + command.getUuid().toString());

			this.procesUseCase.deleteByCode(command.getUuid().toString());
			return withSuccess();
		} catch (Exception e) {
			return withFailure();
		}
	}
}
