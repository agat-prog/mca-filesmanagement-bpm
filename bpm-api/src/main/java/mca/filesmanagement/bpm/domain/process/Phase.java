package mca.filesmanagement.bpm.domain.process;

import java.util.Date;
import java.util.List;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * Fase padre.
 *
 * @author agat
 */
public abstract class Phase {

	private Long id;

	/** FASE_INICIAL, FASE_.... Código de fase */
	private PhaseCodeEnum code;

	/** descripción del tipo de fase --> Fase Inicial, etc. */
	private String description;
	private Date date;
	private Date dateFinished;
	private String user;
	private String userFinished;
	private boolean hasChanged = false;

	/** Constructor por defecto. */
	protected Phase() {
		super();
	}

	/**
	 * Indica si dado un código de fase, la fase actual es compatible.
	 * @param toPhase Código de fase por la que se cuestiona.
	 * @return true si es compatible o false en caso contrario.
	 */
	protected abstract boolean isNavegableTo(PhaseCodeEnum toPhase);

	/**
	 * Indica si la fase actual pertenece a una fase de finalización.
	 * @return true si es una fase finalizada o false en caso contrario.
	 */
	protected abstract boolean isFinish();

	/**
	 * Devuelve los códigos de las fases disponibles.
	 *
	 * @return Lista.
	 */
	public abstract List<PhaseCodeEnum> availablesPhase();

	/**
	 * Finalización de una fase por parte de un usuario.
	 *
	 * @param user Username del usuario.
	 */
	protected void finalize(String user) {
		this.dateFinished = new Date();
		this.userFinished = user;
		this.hasChanged = true;
	}

	/**
	 * Lleva hasta la siguiente fase.
	 * @param nextPhase Fase hacía la qué tramitar.
	 * @throws BpmException Excepción lanzada en caso de operación no permitida.
	 */
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
		return id;
	}

	/**
	 * @param id the id to set
	 */
	protected void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public PhaseCodeEnum getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	protected void setCode(PhaseCodeEnum code) {
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
