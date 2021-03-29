package com.example.app.service;

import com.example.app.model.Utente;
import com.example.app.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public int getPunteggioUtente(String email) { return utenteRepository.punteggioUtente(email); }

    public void setPunteggioUtente (String email, int punteggio){
        int punteggioProvvisorio = utenteRepository.punteggioUtente(email);
        punteggioProvvisorio+=punteggio;
        utenteRepository.setPunteggioUtente(email,punteggioProvvisorio);
    }
}