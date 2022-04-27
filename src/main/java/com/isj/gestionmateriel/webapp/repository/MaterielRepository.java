package com.isj.gestionmateriel.webapp.repository;

import com.isj.gestionmateriel.webapp.model.entities.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel,Long> {

    Long deleteMaterielByReference(String referene);

    @Modifying
    @Query("delete from Materiel m where m.reference=:reference")
    void deleteMateriel(@Param("reference") String reference);

    Optional<Materiel> findMaterielByReference(String reference);

    Optional<List<Materiel>> findMaterielByReferenceOrNomOrDescription(String motcle1, String motcle2 , String motcle3);

    boolean existsByReferenceIgnoreCase(String reference);

}
