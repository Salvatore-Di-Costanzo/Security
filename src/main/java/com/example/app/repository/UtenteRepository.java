package com.example.app.repository;

import com.example.app.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtenteRepository extends JpaRepository<Utente, String> {

    List<Utente> findAll();

    Utente findUtentiByNome(String nome);
}
