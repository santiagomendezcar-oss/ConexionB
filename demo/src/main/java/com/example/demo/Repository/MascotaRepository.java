package com.ejemplo.veterinaria.repository;

import com.ejemplo.veterinaria.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByEspecie(String especie);
    List<Mascota> findByDuenoContainingIgnoreCase(String dueno);
}