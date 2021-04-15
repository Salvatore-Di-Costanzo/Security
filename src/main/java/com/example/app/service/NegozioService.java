package com.example.app.service;

import com.example.app.model.Negozio;
import com.example.app.repository.NegozioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NegozioService {

    private final NegozioRepository negozioRepository;

    @Autowired
    private NegozioService(NegozioRepository negozioRepository){
        this.negozioRepository = negozioRepository;
    }

    public List<Negozio> findAllbyCategoria (String categoria){
        return negozioRepository.findAllByCategoria(categoria);
    }
}
