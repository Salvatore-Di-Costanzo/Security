package com.example.app.service;

import com.example.app.model.Buono;
import com.example.app.repository.BuonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuonoService{

    private final BuonoRepository buonoRepository;

    @Autowired
    private BuonoService(BuonoRepository buonoRepository){
        this.buonoRepository = buonoRepository;
    }

    public void deleteById(Integer id){
        buonoRepository.deleteByid(id);
    }

    public void save(Buono buono){
        buonoRepository.save(buono);
    }
}


