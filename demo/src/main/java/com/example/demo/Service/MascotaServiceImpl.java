package com.ejemplo.veterinaria.service;

import com.ejemplo.veterinaria.dto.MascotaRequestDTO;
import com.ejemplo.veterinaria.dto.MascotaResponseDTO;
import com.ejemplo.veterinaria.model.Mascota;
import com.ejemplo.veterinaria.repository.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;

    private Mascota convertToEntity(MascotaRequestDTO dto) {
        Mascota mascota = new Mascota();
        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setDueno(dto.getDueno());
        mascota.setTelefono(dto.getTelefono());
        return mascota;
    }

    private MascotaResponseDTO convertToDTO(Mascota mascota) {
        return MascotaResponseDTO.builder()
                .id(mascota.getId())
                .nombre(mascota.getNombre())
                .especie(mascota.getEspecie())
                .raza(mascota.getRaza())
                .edad(mascota.getEdad())
                .dueno(mascota.getDueno())
                .telefono(mascota.getTelefono())
                .build();
    }

    @Override
    @Transactional
    public MascotaResponseDTO crearMascota(MascotaRequestDTO dto) {
        if (dto.getEdad() != null && dto.getEdad() < 0) {
            throw new RuntimeException("La edad no puede ser negativa");
        }
        Mascota mascota = convertToEntity(dto);
        Mascota guardada = mascotaRepository.save(mascota);
        return convertToDTO(guardada);
    }

    @Override
    public List<MascotaResponseDTO> listarTodas() {
        return mascotaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MascotaResponseDTO> buscarPorId(Long id) {
        return mascotaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<MascotaResponseDTO> buscarPorEspecie(String especie) {
        return mascotaRepository.findByEspecie(especie).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MascotaResponseDTO> buscarPorDueno(String dueno) {
        return mascotaRepository.findByDuenoContainingIgnoreCase(dueno).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MascotaResponseDTO actualizarMascota(Long id, MascotaRequestDTO dto) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));

        if (dto.getEdad() != null && dto.getEdad() < 0) {
            throw new RuntimeException("La edad no puede ser negativa");
        }

        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setDueno(dto.getDueno());
        mascota.setTelefono(dto.getTelefono());

        return convertToDTO(mascotaRepository.save(mascota));
    }

    @Override
    @Transactional
    public void eliminarMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada con id: " + id);
        }
        mascotaRepository.deleteById(id);
    }
}