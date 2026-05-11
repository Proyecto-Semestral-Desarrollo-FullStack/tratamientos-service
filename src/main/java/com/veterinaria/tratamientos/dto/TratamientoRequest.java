package com.veterinaria.tratamientos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TratamientoRequest {

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private String indicaciones;

    @NotNull(message = "El citaId es obligatorio")
    private Long citaId;
}