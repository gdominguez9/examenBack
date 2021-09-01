package com.example.pruebasExamen.Espanol.infrastructure.repository;

import com.example.pruebasExamen.Espanol.domain.Espanol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspanolRepository extends JpaRepository<Espanol, Integer> {

    Espanol getByPalabra(String palabra);

}
