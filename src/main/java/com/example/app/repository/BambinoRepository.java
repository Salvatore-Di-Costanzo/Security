package com.example.app.repository;

import com.example.app.DTO.UtenteBambino;
import com.example.app.model.Bambino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BambinoRepository extends JpaRepository<Bambino,Integer> {

    List<Bambino> findAll();

    @Query("b.utente FROM Bambino b")
    List<Bambino> getUtenti();

}
