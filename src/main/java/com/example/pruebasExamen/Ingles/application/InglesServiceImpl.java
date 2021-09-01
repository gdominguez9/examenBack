package com.example.pruebasExamen.Ingles.application;

import com.example.pruebasExamen.Espanol.infrastructure.repository.EspanolRepository;
import com.example.pruebasExamen.Ingles.domain.Ingles;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input.InglesInputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesOutputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesSimpleOutputDto;
import com.example.pruebasExamen.Ingles.infrastructure.repository.InglesRepository;
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
public class InglesServiceImpl implements InglesService{

    @Autowired
    private InglesRepository inglesRepository;

    @Autowired
    private EspanolRepository espanolRepository;

    @Override
    public ResponseEntity<InglesOutputDto> saveIngles(InglesInputDto inglesInputDto){

        HttpHeaders headers = new HttpHeaders();

        //Convert InputDto to Entity
        Ingles ingles = new Ingles(inglesInputDto, espanolRepository);

        if(!contieneSoloLetras(inglesInputDto.getPalabra())) {
            headers.add("Information", "Error: Palabra mustn't contain numbers");
            return new ResponseEntity<InglesOutputDto>(null,headers,400);
        }

        if(inglesRepository.getByPalabra(inglesInputDto.getPalabra())!=null){
            headers.add("Information", "Error: This word already exists");
            return new ResponseEntity<InglesOutputDto>(null,headers,406); //409
        }

        if(espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol())==null){
            headers.add("Information", "Error: Respective word in spanish doesn't exist");
            return new ResponseEntity<InglesOutputDto>(null,headers,406);
        }

        //Save
        inglesRepository.save(ingles);

        //Convert Entity to SimpleOutputDto
        InglesOutputDto inglesOutputDto = new InglesOutputDto(ingles);

        headers.add("Information", "POST works");
        return new ResponseEntity<InglesOutputDto>(inglesOutputDto,headers,202);
    }

    @Override
    public ResponseEntity<InglesOutputDto> getPalabraIngles(String palabra) {

        HttpHeaders headers = new HttpHeaders();

        if(inglesRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new ResponseEntity<InglesOutputDto>(null,headers,404);
        }

        Ingles ingles = inglesRepository.getByPalabra(palabra);

        InglesOutputDto inglesOutputDto = new InglesOutputDto(ingles);

        headers.add("Information", "GET works");
        return new ResponseEntity<InglesOutputDto>(inglesOutputDto,headers,200);
    }

    @Override
    public ResponseEntity<List<InglesOutputDto>> getIngles() {

        HttpHeaders headers = new HttpHeaders();

        List<InglesOutputDto> inglesOutputDtoList = new ArrayList<>();
        List<Ingles> inglesList = inglesRepository.findAll();

        for (Ingles espanol : inglesList){
            inglesOutputDtoList.add(new InglesOutputDto(espanol));
        }

        headers.add("Information", "GET works");
        return new ResponseEntity<List<InglesOutputDto>>(inglesOutputDtoList,headers,200);
    }

    @Override
    public ResponseEntity<InglesSimpleOutputDto> deletePalabraIngles(String palabra){

        HttpHeaders headers = new HttpHeaders();

        if(inglesRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new ResponseEntity<InglesSimpleOutputDto>(null,headers,404);
        }

        Ingles ingles = inglesRepository.getByPalabra(palabra);

        inglesRepository.delete(ingles);

        headers.add("Information", "DELETE works");
        return new ResponseEntity<InglesSimpleOutputDto>(null,headers,200); //204
    }

    @Override
    public ResponseEntity<InglesSimpleOutputDto> deleteIngles(){

        HttpHeaders headers = new HttpHeaders();

        inglesRepository.deleteAll();

        headers.add("Information", "DELETE works");
        return new ResponseEntity<InglesSimpleOutputDto>(null,headers,200); //204
    }

    @Override
    public ResponseEntity<InglesOutputDto> updateIngles(InglesInputDto inglesInputDto, String palabra){

        HttpHeaders headers = new HttpHeaders();

        Ingles ingles = inglesRepository.getByPalabra(palabra);

        if(espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol())==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new ResponseEntity<InglesOutputDto>(null,headers,406);
        }

        //Si existe, actualizo los campos no nulos
        if(inglesInputDto.getPalabra()!=null) {
            if(!contieneSoloLetras(inglesInputDto.getPalabra())) {
                headers.add("Information", "Error: Palabra mustn't contain numbers");
                return new ResponseEntity<InglesOutputDto>(null,headers,400);
            }
            else
                ingles.setPalabra(inglesInputDto.getPalabra());
        }
        if(inglesInputDto.getPalabraEspanol()!=null) ingles.setEspanol(espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol()));
        ingles.setFecha_modif(new Date());

        inglesRepository.save(ingles);

        InglesOutputDto inglesOutputDto = new InglesOutputDto(ingles);

        headers.add("Information", "UPDATE works");
        return new ResponseEntity<InglesOutputDto>(inglesOutputDto,headers,200);
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
