package com.example.app.repository;

import com.example.app.model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface UtentiRepository extends JpaRepository<Utenti, Integer> {

    List<Utenti> findAll();
}
