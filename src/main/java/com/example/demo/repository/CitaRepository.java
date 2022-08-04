package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Cita;

import java.util.Date;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long>, JpaSpecificationExecutor<Cita>{
	
	@Query(value = "SELECT c FROM Cita c WHERE c.fechaInicio = ?1 AND c.horaInicio = ?2 AND c.ubicacion = ?3 AND c.direccion = ?4  AND c.estatus = 'Agendada'")
	List<Cita> buscarCitasRegistradas(Date fechaInicio, Date horaInicio, String ubicacion,String direccion);
	
	@Override
    List<Cita> findAll();
}
