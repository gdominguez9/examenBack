package com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output;

import com.example.pruebasExamen.Espanol.domain.Espanol;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesSimpleOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class EspanolOutputDto extends EspanolSimpleOutputDto{

    private List<InglesSimpleOutputDto> palabrasIngles;

    public EspanolOutputDto(Espanol espanol){
        super(espanol);
        this.palabrasIngles = espanol.getInglesList().stream().map(p -> new InglesSimpleOutputDto(p)).collect(Collectors.toList());
    }

}
