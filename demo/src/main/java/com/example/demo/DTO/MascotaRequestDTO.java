package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MascotaRequestDTO {
    package com.ejemplo.veterinaria.dto;

import lombok.*;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class MascotaRequestDTO {
        private String nombre;
        private String especie;
        private String raza;
        private Integer edad;
        private String dueno;
        private String telefono;
    }
}
