package com.ejemplo.veterinaria.controller;

import com.ejemplo.veterinaria.dto.MascotaRequestDTO;
import com.ejemplo.veterinaria.dto.MascotaResponseDTO;
import com.ejemplo.veterinaria.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<?> crearMascota(@RequestBody MascotaRequestDTO dto) {
        try {
            MascotaResponseDTO creada = mascotaService.crearMascota(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping
    public ResponseEntity<List<MascotaResponseDTO>> listarTodas() {
        List<MascotaResponseDTO> mascotas = mascotaService.listarTodas();
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> buscarPorId(@PathVariable Long id) {
        return mascotaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<MascotaResponseDTO>> buscarPorEspecie(@PathVariable String especie) {
        List<MascotaResponseDTO> mascotas = mascotaService.buscarPorEspecie(especie);
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/dueno")
    public ResponseEntity<List<MascotaResponseDTO>> buscarPorDueno(@RequestParam String nombre) {
        List<MascotaResponseDTO> mascotas = mascotaService.buscarPorDueno(nombre);
        return ResponseEntity.ok(mascotas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMascota(@PathVariable Long id, @RequestBody MascotaRequestDTO dto) {
        try {
            MascotaResponseDTO actualizada = mascotaService.actualizarMascota(id, dto);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMascota(@PathVariable Long id) {
        try {
            mascotaService.eliminarMascota(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Mascota eliminada correctamente");
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}