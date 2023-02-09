package mca.filesmanagement.bpm.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import mca.filesmanagement.bpm.infraestructure.model.PhaseEntity;

@Repository
public interface JpaFhaseRepository extends JpaRepository<PhaseEntity, Long> {

	/**
	 * Devuelve una entidad de fase a partir de su código externo.
	 * @param code Código externo de la fase.
	 * @return Entidad de fase.
	 */
	PhaseEntity findByCode(PhaseCodeEnum code);
}
