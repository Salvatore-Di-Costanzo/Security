package com.example.app.repository;

import com.example.app.model.Bambino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BambinoRepository extends JpaRepository<Bambino,Integer> {

    List<Bambino> findAll();

}
