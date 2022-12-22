package mca.filesmanagement.bpm.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.bpm.infraestructure.model.PhaseInstanceEntity;

@Repository
public interface JpaPhaseInstanceRepository extends JpaRepository<PhaseInstanceEntity, Long> {
	
	@Query(value = "SELECT p FROM PhaseInstanceEntity p WHERE p.proces.id = :idProces ORDER BY p.date ASC")
	List<PhaseInstanceEntity> findPhasesByProcess(@Param("idProces") long idProces);
	
	@Modifying
	@Query("DELETE PhaseInstanceEntity p WHERE p.proces.id = :idProces")
	void deleteByProcess(@Param("idProces") long idProces);
}
