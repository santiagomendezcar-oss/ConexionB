package com.ejemplo.veterinaria.service;

import com.ejemplo.veterinaria.dto.MascotaRequestDTO;
import com.ejemplo.veterinaria.dto.MascotaResponseDTO;
import java.util.List;
import java.util.Optional;

public interface MascotaService {
    MascotaResponseDTO crearMascota(MascotaRequestDTO dto);
    List<MascotaResponseDTO> listarTodas();
    Optional<MascotaResponseDTO> buscarPorId(Long id);
    List<MascotaResponseDTO> buscarPorEspecie(String especie);
    List<MascotaResponseDTO> buscarPorDueno(String dueno);
    MascotaResponseDTO actualizarMascota(Long id, MascotaRequestDTO dto);
    void eliminarMascota(Long id);
}