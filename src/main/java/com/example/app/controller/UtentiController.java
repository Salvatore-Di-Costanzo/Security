package com.example.app.controller;

import com.example.app.model.Bambino;
import com.example.app.model.Utente;
import com.example.app.service.BambinoService;
import com.example.app.service.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@CrossOrigin
@Slf4j
public class UtentiController {


    private final UtenteService utenteService;

    private final BambinoService bambinoService;

    @Autowired
    private UtentiController(UtenteService utenteService, BambinoService bambinoService) {
        this.utenteService = utenteService;
        this.bambinoService = bambinoService;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {

        request.logout();
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/index")
    public String home(Model model, HttpServletRequest request) {

        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String name = session.getToken().getPreferredUsername();

        model.addAttribute("name", name);
        model.addAttribute("email",session.getToken().getPhoneNumber());
        return "home";
    }

    @GetMapping("/sub")
    public String subscription(Model m){
        return "subscription";
    }

    @GetMapping("/utenti")
    @ResponseBody
    public List<Utente> getAllUtenti() {
        return utenteService.getUtenti();
    }

    @GetMapping("/bambini")
    @ResponseBody
    public List<Bambino> getAllBambini() {
        return bambinoService.getAllBambini();
    }

    @PostMapping("/userByBimbo")
    @ResponseBody
    public Utente getUtenteByBimbo(@PathParam("id") int id){
        return bambinoService.getUtente(id);
    }

    @PostMapping("/punteggioUtente")
    @ResponseBody
    public int getPunteggioUtente(@PathParam("email") String email) { return utenteService.getPunteggioUtente(email); }

    @PostMapping("/setPunteggio")
    @ResponseBody
    public void setPunteggioUtente(@PathParam("email") String email, @PathParam("punteggio") int punteggio){
        utenteService.setPunteggioUtente(email,punteggio);
    }


}
