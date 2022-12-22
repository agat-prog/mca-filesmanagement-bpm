package mca.filesmanagement.bpm;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.eventuate.common.spring.jdbc.EventuateCommonJdbcOperationsConfiguration;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import mca.filesmanagement.bpm.port.in.IProcesUseCase;
import mca.filesmanagement.bpm.service.BpmCommandHandler;

@Configuration
@Import(EventuateCommonJdbcOperationsConfiguration.class)
@EnableAutoConfiguration
@EnableJpaRepositories
public class BpmSagaConfig {
	
	@Bean
	public BpmCommandHandler bpmCommandHandler(IProcesUseCase procesUseCase) {
		return new BpmCommandHandler(procesUseCase);
	}

	@Bean
	public CommandDispatcher consumerCommandDispatcher(BpmCommandHandler target,
			SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
		return sagaCommandDispatcherFactory.make("bpmCommandDispatcher", target.commandHandlerDefinitions());
	}
}
