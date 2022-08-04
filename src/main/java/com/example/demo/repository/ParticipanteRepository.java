package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long>{

	@Query(value = "SELECT * FROM ADTST_PARTICIPANTES WHERE ID_CITA = ?1 AND CVE_TIPO_PACIENTE = ?2 AND IND_ACTIVO = 1 limit 1", nativeQuery = true)
	Participante pacientePrincipalbyCita(Long id, Integer status);
}
