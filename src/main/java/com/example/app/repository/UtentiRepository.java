package com.example.app.repository;

import com.example.app.model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UtentiRepository extends JpaRepository<Utenti, Integer> {

    List<Utenti> findAll();

    Utenti findUtentiByNome(String nome);
}
