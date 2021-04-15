package com.example.app.controller;

import com.example.app.dto.Bottone;
import com.example.app.model.Sequenziale;
import com.example.app.model.Utente;
import com.example.app.repository.SequenzialeRepository;
import com.example.app.service.UtenteService;
import com.example.app.util.Mail;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UtentiController {


    private final UtenteService utenteService;
    private final Map<String, Integer> mappa = new HashMap<>();

    @Autowired
    private UtentiController(UtenteService utenteService) {
        this.utenteService = utenteService;
        mappa.put("bottone1", -100);
        mappa.put("bottone2", -150);
        mappa.put("bottone3", -200);
        mappa.put("bottone4", -250);
    }

    @GetMapping("/utenti")
    public List<Utente> getAllUtenti() {
        return utenteService.getUtenti();
    }

    @PostMapping("/punteggioUtente")
    public int getPunteggioUtente(@PathParam("email") String email) { return utenteService.getPunteggioUtente(email); }

    @PostMapping("/updatePunteggio")
    public String updatePunteggio(HttpServletRequest request, @RequestBody Bottone bottone) {

        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername().toUpperCase();

        utenteService.setPunteggioUtente(username,mappa.get(bottone.getBottone()));
        return "success";
    }
    @PostMapping("/invioMail")
    public void invioMail(@RequestBody Bottone bottone){
        Mail.sendMail("emaildestinatario",Integer.parseInt(bottone.getBottone()));
    }


}

