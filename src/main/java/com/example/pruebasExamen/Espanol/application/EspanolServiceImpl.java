package com.example.pruebasExamen.Espanol.application;

import com.example.pruebasExamen.Espanol.domain.Espanol;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolSimpleOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input.EspanolnputDto;
import com.example.pruebasExamen.Espanol.infrastructure.repository.EspanolRepository;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesSimpleOutputDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EspanolServiceImpl implements EspanolService{

    @Autowired
    private EspanolRepository espanolRepository;


    @Override
    public ResponseEntity<EspanolSimpleOutputDto>  saveEspanol(EspanolnputDto espanolnputDto) {

        HttpHeaders headers = new HttpHeaders();

        //Convert InputDto to Entity
        Espanol espanol = new Espanol(espanolnputDto);

        if(!contieneSoloLetras(espanolnputDto.getPalabra())) {
            headers.add("Information", "Error: Palabra mustn't contain numbers");
            return new ResponseEntity<EspanolSimpleOutputDto>(null,headers,400);
        }


        if(espanolRepository.getByPalabra(espanolnputDto.getPalabra())!=null){
            headers.add("Information", "Error: This word already exists");
            return new ResponseEntity<EspanolSimpleOutputDto>(null,headers,409);
        }

        //Save
        espanolRepository.save(espanol);

        //Convert Entity to SimpleOutputDto
        EspanolSimpleOutputDto espanolSimpleOutputDto = new EspanolSimpleOutputDto(espanol);

        headers.add("Information", "POST works");
        return new ResponseEntity<EspanolSimpleOutputDto>(espanolSimpleOutputDto,headers,202);
    }

    @Override
    public ResponseEntity<EspanolOutputDto> getPalabraEspanol(String palabra) {

        HttpHeaders headers = new HttpHeaders();

        if(espanolRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new ResponseEntity<EspanolOutputDto>(null,headers,404);
        }

        Espanol espanol = espanolRepository.getByPalabra(palabra);

        EspanolOutputDto espanolOutputDto = new EspanolOutputDto(espanol);

        headers.add("Information", "GET works");
        return new ResponseEntity<EspanolOutputDto>(espanolOutputDto,headers,200);
    }

    @Override
    public ResponseEntity<List<EspanolOutputDto>> getEspanol() {

        HttpHeaders headers = new HttpHeaders();

        List<EspanolOutputDto> espanolOutputDtoList = new ArrayList<>();
        List<Espanol> espanolList = espanolRepository.findAll();

        for (Espanol espanol : espanolList){
            espanolOutputDtoList.add(new EspanolOutputDto(espanol));
        }

        headers.add("Information", "GET works");
        return new ResponseEntity<List<EspanolOutputDto>>(espanolOutputDtoList,headers,200);
    }

    @Override
    public ResponseEntity<EspanolSimpleOutputDto> deletePalabraEspanol(String palabra){

        HttpHeaders headers = new HttpHeaders();

        if(espanolRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new ResponseEntity<EspanolSimpleOutputDto>(null,headers,404);
        }

        Espanol espanol = espanolRepository.getByPalabra(palabra);

        espanolRepository.delete(espanol);

        headers.add("Information", "DELETE works");
        return new ResponseEntity<EspanolSimpleOutputDto>(null,headers,204);
    }

    @Override
    public ResponseEntity<InglesSimpleOutputDto> deleteEspanol(){ //throws Exception {

        HttpHeaders headers = new HttpHeaders();

        espanolRepository.deleteAll();

        headers.add("Information", "DELETE works");
        return new ResponseEntity<InglesSimpleOutputDto>(null,headers,200);  //204
    }

    @Override
    public ResponseEntity<EspanolSimpleOutputDto>  updateEspanol(EspanolnputDto espanolInputDto, String palabra) {

        HttpHeaders headers = new HttpHeaders();

        Espanol espanol = espanolRepository.getByPalabra(palabra);

        if(espanolRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new ResponseEntity<EspanolSimpleOutputDto>(null,headers,406);
        }

        //Si existe, actualizo los campos no nulos
        if(espanolInputDto.getPalabra()!=null) {
            if(!contieneSoloLetras(espanolInputDto.getPalabra())) {
                headers.add("Information", "Error: Palabra mustn't contain numbers");
                return new ResponseEntity<EspanolSimpleOutputDto>(null,headers,400);
            }
            else
                espanol.setPalabra(espanolInputDto.getPalabra());
        }
        if(espanolInputDto.getDescripcion()!=null) espanol.setDescripcion(espanolInputDto.getDescripcion());
        espanol.setFecha_modif(new Date());

        espanolRepository.save(espanol);

        EspanolSimpleOutputDto espanolSimpleOutputDto = new EspanolSimpleOutputDto(espanol);

        headers.add("Information", "UPDATE works");
        return new ResponseEntity<EspanolSimpleOutputDto>(espanolSimpleOutputDto,headers,200);
    }


    public static boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }


}

