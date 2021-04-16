package com.example.app.repository;

import com.example.app.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoRepository extends JpaRepository<Tipo,Integer> {
    List<Tipo> findAll();
}
