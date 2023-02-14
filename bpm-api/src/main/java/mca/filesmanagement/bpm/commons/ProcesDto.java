package mca.filesmanagement.bpm.commons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * DTO de un proceso.
 *
 * @author agat
 */
public class ProcesDto {

	private Long id;
	private String code;
	private boolean active;
	private Date date;
	private List<PhaseInstanceDto> phases = new ArrayList<>(0);

	/** Constructor por defecto. */
	public ProcesDto() {
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * @return the phases
	 */
	public List<PhaseInstanceDto> getPhases() {
		return phases;
	}

	/**
	 * Añade una instancia de fase a la lista.
	 * @param phaseDto
	 */
	public void addPhase(PhaseInstanceDto phaseDto) {
		if (Objects.nonNull(phaseDto)) {
			this.phases.add(phaseDto);
		}
	}
}
