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