package mca.filesmanagement.bpm.domain.process;

import java.util.Date;
import java.util.List;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PHASE_CODE;

public abstract class Phase {
	
	private Long Id;
	
	/** FASE_INICIAL, FASE_.... Código de fase */
	private PHASE_CODE code;
	
	/** descripción del tipo de fase --> Fase Inicial, etc. */
	private String description;
	private Date date;
	private Date dateFinished;	
	private String user;
	private String userFinished;
	private boolean hasChanged = false;
	
	protected Phase() {
		super();
	}
	
	protected abstract boolean isNavegableTo(PHASE_CODE toPhase);
	protected abstract boolean isFinish();
	public abstract List<PHASE_CODE> availablesPhase();
	
	protected void finalize(String user) {
		this.dateFinished = new Date();
		this.userFinished = user;
		this.hasChanged = true;
	}
	
	protected void next(Phase nextPhase) throws BpmException {
		if (!this.isNavegableTo(nextPhase.getCode())){
			throw new BpmException("Operación no permitida");
		}
		
		this.finalize(nextPhase.getUser());
		
		if (nextPhase.isFinish()) {
			nextPhase.finalize(nextPhase.getUser());
		}
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	protected void setId(Long id) {
		Id = id;
	}

	/**
	 * @return the code
	 */
	public PHASE_CODE getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	protected void setCode(PHASE_CODE code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	protected void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	protected void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the dateFinished
	 */
	public Date getDateFinished() {
		return dateFinished;
	}

	/**
	 * @param dateFinished the dateFinished to set
	 */
	protected void setDateFinished(Date dateFinished) {
		this.dateFinished = dateFinished;
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
	protected void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the userFinished
	 */
	public String getUserFinished() {
		return userFinished;
	}

	/**
	 * @param userFinished the userFinished to set
	 */
	protected void setUserFinished(String userFinished) {
		this.userFinished = userFinished;
	}

	/**
	 * @return the hasChanged
	 */
	protected boolean isHasChanged() {
		return hasChanged;
	}

	/**
	 * @param hasChanged the hasChanged to set
	 */
	protected void setHasChanged(boolean hasChanged) {
		this.hasChanged = hasChanged;
	}
}
