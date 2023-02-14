package bpm.api.messaging.commands;

import io.eventuate.tram.commands.common.Command;

/**
 * Comando de creación de un proceso BPM.
 *
 * @author agat
 */
public class CreateBpmCommand implements Command {

	private String user;

	/***/
	public CreateBpmCommand() {
		super();
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
}
