package com.example.app.controller;

import com.example.app.model.Bambino;
import com.example.app.model.Utente;
import com.example.app.service.BambinoService;
import com.example.app.service.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class UtentiController {


    private final UtenteService utenteService;

    private final BambinoService bambinoService;

    @Autowired
    private UtentiController(UtenteService utenteService, BambinoService bambinoService) {
        this.utenteService = utenteService;
        this.bambinoService = bambinoService;
    }

    @GetMapping("/utenti")
    public List<Utente> getAllUtenti() {
        return utenteService.getUtenti();
    }

    @GetMapping("/bambini")
    public List<Bambino> getAllBambini() {
        return bambinoService.getAllBambini();
    }

    @PostMapping("/userByBimbo")
    public Utente getUtenteByBimbo(@PathParam("id") int id){
        return bambinoService.getUtente(id);
    }

    @PostMapping("/punteggioUtente")
    public int getPunteggioUtente(@PathParam("email") String email) { return utenteService.getPunteggioUtente(email); }

    @PostMapping("/setPunteggio")
    public void setPunteggioUtente(@PathParam("email") String email, @PathParam("punteggio") int punteggio){
        utenteService.setPunteggioUtente(email,punteggio);
    }


}
