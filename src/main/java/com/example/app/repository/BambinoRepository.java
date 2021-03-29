package com.example.app.repository;

import com.example.app.model.Bambino;
import com.example.app.model.Utente;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Configuration
public interface BambinoRepository extends JpaRepository<Bambino,Integer> {

    List<Bambino> findAll();

    @Query("SELECT b.utente from Bambino b where b.id = :id")
    Utente getUtente(@Param("id") int id);

}
