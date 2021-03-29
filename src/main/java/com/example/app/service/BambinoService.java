package com.example.app.service;

import com.example.app.model.Bambino;
import com.example.app.model.Utente;
import com.example.app.repository.BambinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BambinoService {

    private final BambinoRepository bambinoRepository;

    @Autowired
    private BambinoService (BambinoRepository bambinoRepository){
        this.bambinoRepository = bambinoRepository;
    }

    public List<Bambino> getAllBambini (){
        return bambinoRepository.findAll();
    }

    public Utente getUtente (int id) {return bambinoRepository.getUtente(id);};
}
