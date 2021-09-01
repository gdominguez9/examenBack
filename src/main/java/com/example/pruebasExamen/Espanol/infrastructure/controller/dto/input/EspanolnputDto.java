package com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspanolnputDto {

    @NotNull(message = "palabra cannot be null")
    private String palabra;

    private String descripcion;

}
