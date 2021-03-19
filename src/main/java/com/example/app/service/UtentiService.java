package com.example.app.service;

import com.example.app.model.Utenti;
import com.example.app.repository.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtentiService {

    private final UtentiRepository utentiRepository;

    @Autowired
    private UtentiService (UtentiRepository utentiRepository){
        this.utentiRepository = utentiRepository;
    }

    public List<Utenti> getUtenti () {
        return utentiRepository.findAll();
    }

    public Utenti getUtente(String nome) {
        return utentiRepository.findUtentiByNome(nome);
    }
}
