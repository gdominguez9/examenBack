package com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InglesInputDto {

    @NotNull(message = "palabra cannot be null")
    private String palabra;

    private String palabraEspanol;

}
