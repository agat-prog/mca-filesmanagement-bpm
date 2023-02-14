package mca.filesmanagement.bpm.commons;

/**
 * DTO de la Phase.
 *
 * @author agat
 */
public class PhaseDto {
	private Long id;
	private PhaseCodeEnum code;
	private String description;

	/** Constructor por defecto. */
	public PhaseDto() {
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
	public PhaseCodeEnum getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(PhaseCodeEnum code) {
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
	public void setDescription(String description) {
		this.description = description;
	}
}
