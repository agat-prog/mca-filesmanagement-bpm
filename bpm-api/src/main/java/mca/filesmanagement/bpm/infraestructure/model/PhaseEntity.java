package mca.filesmanagement.bpm.infraestructure.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

@Entity
@Table(name = "PHASES")
public class PhaseEntity {

	@Id
	@Column (name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column (name = "CODE")
	@Enumerated(EnumType.STRING)
	private PhaseCodeEnum code = PhaseCodeEnum.NULL;

	@Column (name = "DESCRIPTION")
	private String description;

	/** Constructor por defecto. */
	public PhaseEntity() {
		super();
	}

	/**
	 * @return the id.
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
		if (Objects.isNull(code)) {
			this.code = PhaseCodeEnum.NULL;
		} else {
			this.code = code;
		}
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
