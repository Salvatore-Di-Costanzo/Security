package com.example.app.repository;

import com.example.app.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoRepository extends JpaRepository<Tipo,Integer> {
    @Query("Select t from Tipo t")
    List<Tipo> getAllTipo();
}
