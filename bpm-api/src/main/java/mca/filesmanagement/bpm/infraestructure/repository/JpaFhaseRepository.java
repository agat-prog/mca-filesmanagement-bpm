package mca.filesmanagement.bpm.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.bpm.commons.PHASE_CODE;
import mca.filesmanagement.bpm.infraestructure.model.PhaseEntity;

@Repository
public interface JpaFhaseRepository extends JpaRepository<PhaseEntity, Long> {

	PhaseEntity findByCode(PHASE_CODE code);
}
