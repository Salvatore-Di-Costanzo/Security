package com.example.app.service;

import com.example.app.model.Tipo;
import com.example.app.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {
    private final TipoRepository tipoRepository;

    @Autowired
    private TipoService(TipoRepository tipoRepository){
        this.tipoRepository = tipoRepository;
    }

    public List<Tipo> findAll(){
        return tipoRepository.findAll();
    }
}
