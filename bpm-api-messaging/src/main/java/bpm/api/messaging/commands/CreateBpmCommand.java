package bpm.api.messaging.commands;

import io.eventuate.tram.commands.common.Command;

public class CreateBpmCommand implements Command {
	
	private String user;
	
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
