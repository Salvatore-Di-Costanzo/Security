package com.example.app.repository;

import com.example.app.model.Negozio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegozioRepository extends JpaRepository<Negozio,Integer> {
    List<Negozio> findAllByCategoria (String categoria);
}
