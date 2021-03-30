package com.example.app.service;

import com.example.app.DTO.BambiniUtenti;
import com.example.app.model.Bambino;
import com.example.app.model.Utente;
import com.example.app.repository.BambinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BambinoService {

    private final BambinoRepository bambinoRepository;

    @Autowired
    private BambinoService (BambinoRepository bambinoRepository){
        this.bambinoRepository = bambinoRepository;
    }

    public List<Bambino> getAllBambini (){
        return bambinoRepository.findAll();
    }

    public Utente getUtente (int id) {return bambinoRepository.getUtente(id);}

    public List<BambiniUtenti> getAllUtenti () {
        List<Utente> Utenti = bambinoRepository.getAllUtenti();
        List<Bambino> Bambini = bambinoRepository.findAll();
        List<BambiniUtenti> bambiniUtenti = new ArrayList<>();

        for(int i=0;i<Utenti.size();i++)
            bambiniUtenti.add(new BambiniUtenti(Bambini.get(i).getNomeBambino(),Bambini.get(i).getCognomeBambino(),Utenti.get(i).getEmail()));
        return bambiniUtenti;
    }
}
