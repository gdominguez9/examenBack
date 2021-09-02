package com.example.pruebasExamen.Espanol.application;

import com.example.pruebasExamen.Espanol.domain.Espanol;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input.EspanolnputDto;
import com.example.pruebasExamen.Espanol.infrastructure.repository.EspanolRepository;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.RespuestaEspanol;
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
public class EspanolServiceImpl implements EspanolService{

    @Autowired
    private EspanolRepository espanolRepository;


    @Override
    public RespuestaEspanol saveEspanol(EspanolnputDto espanolnputDto) {

        HttpHeaders headers = new HttpHeaders();

        //Convert InputDto to Entity
        Espanol espanol = new Espanol(espanolnputDto);

        if(!espanolnputDto.getPalabra().chars().allMatch(Character::isLetter)) {
            headers.add("Information", "Error: Palabra mustn't contain numbers");
            return new RespuestaEspanol(null, headers,400);
        }


        if(espanolRepository.getByPalabra(espanolnputDto.getPalabra())!=null){
            headers.add("Information", "Error: This word already exists");
            return new RespuestaEspanol(null, headers,409);
        }

        //Save
        espanolRepository.save(espanol);

        List<Espanol> body = new ArrayList<>();
        body.add(espanol);

        headers.add("Information", "POST works");
        return new RespuestaEspanol(body, headers,202);
    }

    @Override
    public RespuestaEspanol getPalabraEspanol(String palabra) {

        HttpHeaders headers = new HttpHeaders();

        if(espanolRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new RespuestaEspanol(null, headers,404);
        }

        Espanol espanol = espanolRepository.getByPalabra(palabra);

        List<Espanol> body = new ArrayList<>();
        body.add(espanol);

        headers.add("Information", "GET works");
        return new RespuestaEspanol(body, headers,200);
    }

    @Override
    public RespuestaEspanol getEspanol() {

        HttpHeaders headers = new HttpHeaders();

        List<Espanol> espanolList = espanolRepository.findAll();

        headers.add("Information", "GET works");
        return new RespuestaEspanol(espanolList,headers,200);
    }

    @Override
    public RespuestaEspanol deletePalabraEspanol(String palabra){

        HttpHeaders headers = new HttpHeaders();

        if(espanolRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new RespuestaEspanol(null, headers,404);
        }

        Espanol espanol = espanolRepository.getByPalabra(palabra);

        espanolRepository.delete(espanol);

        headers.add("Information", "DELETE works");
        return new RespuestaEspanol(null, headers,204);
    }

    @Override
    public RespuestaEspanol deleteEspanol(){ //throws Exception {

        HttpHeaders headers = new HttpHeaders();

        espanolRepository.deleteAll();

        headers.add("Information", "DELETE works");
        return new RespuestaEspanol(null, headers,200);  //204
    }

    @Override
    public RespuestaEspanol  updateEspanol(EspanolnputDto espanolInputDto, String palabra) {

        HttpHeaders headers = new HttpHeaders();

        Espanol espanol = espanolRepository.getByPalabra(palabra);

        if(espanolRepository.getByPalabra(palabra)==null){
            headers.add("Information", "Error: This word doesn't exist");
            return new RespuestaEspanol(null,headers,406);
        }

        //Si existe, actualizo los campos no nulos
        if(espanolInputDto.getPalabra()!=null) {
            if(!espanolInputDto.getPalabra().chars().allMatch(Character::isLetter)) {
                headers.add("Information", "Error: Palabra mustn't contain numbers");
                return new RespuestaEspanol(null, headers,400);
            }
            espanol.setPalabra(espanolInputDto.getPalabra());
        }
        if(espanolInputDto.getDescripcion()!=null) espanol.setDescripcion(espanolInputDto.getDescripcion());
        espanol.setFecha_modif(new Date());

        espanolRepository.save(espanol);

        List<Espanol> body = new ArrayList<>();
        body.add(espanol);

        headers.add("Information", "UPDATE works");
        return new RespuestaEspanol(body, headers,200);
    }

}

