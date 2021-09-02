package com.example.pruebasExamen.Ingles.infrastructure.repository;

import com.example.pruebasExamen.Ingles.domain.Ingles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InglesRepository extends JpaRepository<Ingles, Integer> {

    Ingles getByPalabra(String palabra);

}
