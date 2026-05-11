package com.veterinaria.tratamientos.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TratamientoResponse {
    private Long id;
    private String descripcion;
    private String indicaciones;
    private Long citaId;
}