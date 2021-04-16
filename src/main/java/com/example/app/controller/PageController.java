package com.example.app.controller;

import com.example.app.model.Negozio;
import com.example.app.model.Sequenziale;
import com.example.app.model.Utente;
import com.example.app.repository.SequenzialeRepository;
import com.example.app.service.NegozioService;
import com.example.app.service.TipoService;
import com.example.app.service.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@CrossOrigin
@Slf4j
public class PageController {

    private final UtenteService utenteService;
    private final SequenzialeRepository sequenzialeRepository;
    private final NegozioService negozioService;
    private final TipoService tipoService;

    @Autowired
    private PageController(UtenteService utenteService, SequenzialeRepository sequenzialeRepository, NegozioService negozioService, TipoService tipoService) {
        this.utenteService = utenteService;
        this.sequenzialeRepository = sequenzialeRepository;
        this.negozioService = negozioService;
        this.tipoService = tipoService;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {

        request.logout();
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername().toUpperCase();
        Utente utente = utenteService.getUtenteByEmail(username);
        if (utente != null && session.getToken().getRealmAccess().isUserInRole("admin")) {
            model.addAttribute("nomeUtente", username);
            List<Utente> utenti = utenteService.getUtenti();
            utenti.remove(utente);
            model.addAttribute("Utenti", utenti);
            return ("admin");
        }

        if (utente == null) {
            String name = session.getToken().getGivenName().toUpperCase();
            String surname = session.getToken().getFamilyName().toUpperCase();
            utente = new Utente();
            utente.setEmail(username);
            utente.setNome(name);
            utente.setCognome(surname);
            utente.setPunteggio(0);
            utenteService.saveUtente(utente);
        }
        model.addAttribute("nomeUtente", utente.getNome() + " " + utente.getCognome());
        int punteggio = utenteService.getPunteggioUtente(username);
        String punteggioComposto = "Punteggio: " + punteggio;
        model.addAttribute("punteggio", punteggioComposto);
        if (punteggio >= 100)
            model.addAttribute("abilita1", true);
        if (punteggio >= 150)
            model.addAttribute("abilita2", true);
        if (punteggio >= 200)
            model.addAttribute("abilita3", true);
        if (punteggio >= 250)
            model.addAttribute("abilita4", true);

        Sequenziale sequenziale = sequenzialeRepository.findById(1);
        sequenziale.setNumero(sequenziale.getNumero() + 1);
        sequenzialeRepository.save(sequenziale);
        model.addAttribute("sequenziale", sequenziale.getNumero());
        return "user";
    }


    @PostMapping("/search")
    public String searchUtenti(@RequestParam("dati") String val, Model model, HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername();
        model.addAttribute("nomeUtente", username);
        model.addAttribute("Bambini", utenteService.searchUtenti(val.toUpperCase()));
        return ("admin");
    }

    @PostMapping("/setPunteggio")
    public String setPunteggioUtente(@PathParam("email") String email, @PathParam("punteggio") int punteggio, HttpServletRequest request, Model model) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername();
        model.addAttribute("nomeUtente", username);
        if (email != null) {
            if (utenteService.setPunteggioUtente(email, punteggio).equals("OK"))
                return "success";
            else
                return "fail";
        } else
            return "fail";
    }

    @RequestMapping(path = "/elencoNegozi", method = RequestMethod.GET)
    public String elencoNegozi(Model model, HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername().toUpperCase();
        model.addAttribute("nomeUtente", username);
        List<Negozio> negozi = negozioService.findAll();
        model.addAttribute("Negozi", negozi);
        return "gestioneNegozio";

    }

    @PostMapping("/modificaNegozio")
    public String modificaNegozio(@PathParam("id") int id, HttpServletRequest request, Model model) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername();
        model.addAttribute("nomeUtente", username);
        Negozio negozio = negozioService.findById(id);
        model.addAttribute("negozio", negozio);
        model.addAttribute("tipi",tipoService.getAllTipo());
        return "editForm";
    }

    @PostMapping("/sendEditedStore")
    public String sendEditedStore(@PathParam("negozio")Negozio negozio) {
        try {
            negozioService.saveNegozio(negozio);
            return "successNegozio";
        } catch (Exception e) {
            return "failNegozio";
        }
    }

    @PostMapping("/eliminaNegozio")
    public String eliminaNegozio(@PathParam("id") int id) {
        try {
            negozioService.deleteById(id);
            return "successNegozio";
        } catch (Exception e) {
            return "failNegozio";
        }
    }

    @GetMapping("/returnEditPage")
    public String returnEditPage(HttpServletRequest request, Model model,Negozio negozio){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername();
        model.addAttribute("nomeUtente", username);
        model.addAttribute("tipi",tipoService.getAllTipo());
        return "editForm";
    }

    @PostMapping("/eliminaUtente")
    public String eliminaUtente(@PathParam("email") String email){
        try {
            utenteService.deleteUtenteByEmail(email);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

}
