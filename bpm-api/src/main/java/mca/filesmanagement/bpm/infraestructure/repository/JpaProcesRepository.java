package mca.filesmanagement.bpm.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.bpm.infraestructure.model.ProcesEntity;

@Repository
public interface JpaProcesRepository extends JpaRepository<ProcesEntity, Long> {
	@Query("SELECT p.id FROM ProcesEntity p WHERE p.code = :code")
	Long getIdByCode(@Param("code") String code);
}
