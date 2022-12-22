package mca.filesmanagement.bpm.commons;

public class PhaseDto {
	private Long id;
	private PHASE_CODE code;
	private String description;
	
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
	public PHASE_CODE getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(PHASE_CODE code) {
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
