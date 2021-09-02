package com.example.pruebasExamen.Ingles.application;

import com.example.pruebasExamen.Espanol.infrastructure.repository.EspanolRepository;
import com.example.pruebasExamen.Ingles.domain.Ingles;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input.InglesInputDto;
import com.example.pruebasExamen.Ingles.infrastructure.repository.InglesRepository;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.RespuestaIngles;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public RespuestaIngles saveIngles(InglesInputDto inglesInputDto){

        HttpHeaders headers = new HttpHeaders();

        //Convert InputDto to Entity
        Ingles ingles = new Ingles(inglesInputDto, espanolRepository);

        if(!inglesInputDto.getPalabra().chars().allMatch(Character::isLetter)) {
            headers.add("Information", "Error: Palabra mustn't contain numbers");
            return new RespuestaIngles(null, headers,400);
        }

        if(espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol())==null){
            headers.add("Information", "Error: Respective word in spanish doesn't exist");
            return new RespuestaIngles(null, headers,406);
        }

        if(inglesRepository.getByPalabra(inglesInputDto.getPalabra())!=null){
            if(inglesRepository.getByPalabra(inglesInputDto.getPalabra()).getEspanol().getPalabra().equals(espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol()).getPalabra())) {
                headers.add("Information", "Error: This word already exists");
                return new RespuestaIngles(null, headers, 406); //409
            }
        }


        //Save
        inglesRepository.save(ingles);

        List<Ingles> body = new ArrayList<>();
        body.add(ingles);

        headers.add("Information", "POST works");
        return new RespuestaIngles(body, headers, 202);
    }

    @Override
    public RespuestaIngles getPalabraIngles(String palabra) {

        HttpHeaders headers = new HttpHeaders();

        if(inglesRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new RespuestaIngles(null, headers, 404);
        }

        Ingles ingles = inglesRepository.getByPalabra(palabra);

        List<Ingles> body = new ArrayList<>();
        body.add(ingles);

        headers.add("Information", "GET works");
        return new RespuestaIngles(body, headers,200);
    }

    @Override
    public RespuestaIngles getIngles() {

        HttpHeaders headers = new HttpHeaders();

        List<Ingles> inglesList = inglesRepository.findAll();

        headers.add("Information", "GET works");
        return new RespuestaIngles(inglesList, headers,200);    }

    @Override
    public RespuestaIngles deletePalabraIngles(String palabra){

        HttpHeaders headers = new HttpHeaders();

        if(inglesRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new RespuestaIngles(null,headers,404);
        }

        Ingles ingles = inglesRepository.getByPalabra(palabra);

        inglesRepository.delete(ingles);

        headers.add("Information", "DELETE works");
        return new RespuestaIngles(null,headers,200); //204
    }

    @Override
    public RespuestaIngles deleteIngles(){

        HttpHeaders headers = new HttpHeaders();

        inglesRepository.deleteAll();

        headers.add("Information", "DELETE works");
        return new RespuestaIngles(null,headers,200); //204
    }

    @Override
    public RespuestaIngles updateIngles(InglesInputDto inglesInputDto, String palabra){

        HttpHeaders headers = new HttpHeaders();

        Ingles ingles = inglesRepository.getByPalabra(palabra);

        if(espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol())==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new RespuestaIngles(null,headers,406);
        }

        //Si existe, actualizo los campos no nulos
        if(inglesInputDto.getPalabra()!=null) {
            if(!inglesInputDto.getPalabra().chars().allMatch(Character::isLetter)) {
                headers.add("Information", "Error: Palabra mustn't contain numbers");
                return new RespuestaIngles(null,headers,400);
            }
            ingles.setPalabra(inglesInputDto.getPalabra());
        }
        if(inglesInputDto.getPalabraEspanol()!=null) ingles.setEspanol(espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol()));
        ingles.setFecha_modif(new Date());

        inglesRepository.save(ingles);

        List<Ingles> body = new ArrayList<>();
        body.add(ingles);

        headers.add("Information", "UPDATE works");
        return new RespuestaIngles(body,headers,200);
    }

}
