package com.example.app.controller;

import com.example.app.dto.Bottone;
import com.example.app.dto.BuonoDaRegistrare;
import com.example.app.dto.InvioMailFields;
import com.example.app.model.Buono;
import com.example.app.model.Negozio;
import com.example.app.model.Tipo;
import com.example.app.model.Utente;
import com.example.app.service.BuonoService;
import com.example.app.service.NegozioService;
import com.example.app.service.TipoService;
import com.example.app.service.UtenteService;
import com.example.app.util.Mail;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final NegozioService negozioService;
    private final TipoService tipoService;
    private final BuonoService buonoService;

    @Autowired
    private UtentiController(UtenteService utenteService, NegozioService negozioService,TipoService tipoService,BuonoService buonoService) {
        this.utenteService = utenteService;
        this.negozioService = negozioService;
        mappa.put("caseificio", -100);
        mappa.put("pizzeria", -150);
        mappa.put("pasticceria", -200);
        mappa.put("panificio", -250);
        this.tipoService = tipoService;
        this.buonoService = buonoService;
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
    public void invioMail(@RequestBody InvioMailFields invioMailFields){
        List<Negozio> negozi = negozioService.findAllbyCategoria(invioMailFields.getBottone());
        for(Negozio negozio : negozi){
            Mail.sendMail(negozio.getEmail(),Integer.parseInt(invioMailFields.getSequenziale()));
        }

    }

    @PostMapping("/getTipi")
    public List<Tipo> findAll(){
        return tipoService.getAllTipo();
    }

    @PostMapping("/insertBuono")
    public void inserisciBuono(@RequestBody BuonoDaRegistrare buonoDaRegistrare){
        Buono buono = new Buono();
        buono.setId(buonoDaRegistrare.getNumeroBuono());
        buono.setBeneficiario(buonoDaRegistrare.getNomeUtente());
        buono.setTipologia(buonoDaRegistrare.getTipologia());
        buonoService.save(buono);
    }


}

