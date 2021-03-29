package com.example.app.service;

import com.example.app.model.Utente;
import com.example.app.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtenteService {

    private final UtenteRepository utenteRepository;

    @Autowired
    private UtenteService(UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    public List<Utente> getUtenti () {
        return utenteRepository.findAll();
    }

    public Utente getUtente(String nome) {
        return utenteRepository.findUtentiByNome(nome);
    }
}
