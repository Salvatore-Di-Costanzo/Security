package com.example.app.controller;

import com.example.app.model.Utente;
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

@Controller
@CrossOrigin
@Slf4j
public class PageController {

    private final UtenteService utenteService;

    @Autowired
    private PageController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {

        request.logout();
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername();
        Utente utente = utenteService.getUtenteByEmail(username);
        if(utente != null && session.getToken().getRealmAccess().isUserInRole("admin")) {
            model.addAttribute("nomeUtente",username);
            model.addAttribute("Bambini", utenteService.getUtenti());
            return ("admin");
        }

        if (utente == null){
            String name = session.getToken().getGivenName();
            String surname = session.getToken().getFamilyName();
            utente = new Utente();
            utente.setEmail(username.toUpperCase());
            utente.setNome(name.toUpperCase());
            utente.setCognome(surname.toUpperCase());
            utente.setPunteggio(0);
            utenteService.saveUtente(utente);
        }
        model.addAttribute("nomeUtente",utente.getNome() + " " + utente.getCognome());
        int punteggio = utenteService.getPunteggioUtente(username);
        String punteggioComposto = "Punteggio: " + punteggio;
        model.addAttribute("punteggio",punteggioComposto);
        if(punteggio >= 100 )
            model.addAttribute("abilita1",true);
        if(punteggio >= 150 )
            model.addAttribute("abilita2",true);
        if(punteggio >= 200 )
            model.addAttribute("abilita3",true);
        if(punteggio >= 250 )
            model.addAttribute("abilita4",true);
        return "user";
    }

    @PostMapping("/search")
    public String searchUtenti (@RequestParam("dati")String val,Model model,HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String username = session.getToken().getPreferredUsername();
        model.addAttribute("nomeUtente", username);
        model.addAttribute("Bambini", utenteService.searchUtenti(val));
        return ("admin");
    }
}
