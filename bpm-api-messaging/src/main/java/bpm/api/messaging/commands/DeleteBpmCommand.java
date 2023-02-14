package bpm.api.messaging.commands;

import java.util.UUID;

import io.eventuate.tram.commands.common.Command;

/**
 * Comando de eliminación de un BPM.
 *
 * @author agat
 */
public class DeleteBpmCommand implements Command {

	private UUID uuid;

	/***/
	public DeleteBpmCommand() {
		super();
	}

	/**
	 * Crea una instancia de comando con su uuid inicializado.
	 * @param uuid Identificador.
	 */
	public DeleteBpmCommand(UUID uuid) {
		super();
		this.uuid = uuid;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
