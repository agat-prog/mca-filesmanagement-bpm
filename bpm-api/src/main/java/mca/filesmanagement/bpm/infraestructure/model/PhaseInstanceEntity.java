package mca.filesmanagement.bpm.infraestructure.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mca.filesmanagement.bpm.commons.PHASE_CODE;

@Entity
@Table(name = "PHASE_INSTANCES")
public class PhaseInstanceEntity {

	@Id
	@Column (name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="ID_PHASE", updatable = false, nullable = false)
	private PhaseEntity phase;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="ID_PROCES", updatable = false, nullable = false)
	private ProcesEntity proces;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name = "DATE", nullable = false, updatable = false)
	private Date date;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name = "DATE_FINISHED", nullable = true)
	private Date dateFinished;	
	
	@Column (name = "USER", updatable = false, nullable = false)
	private String user;
	
	@Column (name = "USER_FINISHED", nullable = true)
	private String userFinished;
	
	public PhaseInstanceEntity() {
		super();
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
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the phase
	 */
	public PhaseEntity getPhase() {
		return phase;
	}
	
	public PHASE_CODE getPhaseCode() {
		PHASE_CODE phaseCode = PHASE_CODE.NULL;
		if (Objects.nonNull(this.phase)) {
			phaseCode = this.phase.getCode();
		}
		return phaseCode;
	}
	
	public String getPhaseDescription() {
		String description = null;
		if (Objects.nonNull(this.phase)) {
			description = this.phase.getDescription();
		}
		return description;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(PhaseEntity phase) {
		this.phase = phase;
	}

	/**
	 * @return the proces
	 */
	public ProcesEntity getProces() {
		return proces;
	}

	/**
	 * @param proces the proces to set
	 */
	public void setProces(ProcesEntity proces) {
		this.proces = proces;
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
	public void setDate(Date date) {
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
	public void setDateFinished(Date dateFinished) {
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
	public void setUser(String user) {
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
	public void setUserFinished(String userFinished) {
		this.userFinished = userFinished;
	}
}
