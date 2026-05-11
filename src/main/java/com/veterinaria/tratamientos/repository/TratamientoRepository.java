package com.veterinaria.tratamientos.repository;

import com.veterinaria.tratamientos.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    List<Tratamiento> findByCitaId(Long citaId);
}