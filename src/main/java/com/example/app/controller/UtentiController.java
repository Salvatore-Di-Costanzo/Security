package com.example.app.controller;

import com.example.app.DTO.UtenteBambino;
import com.example.app.configuration.JsonParser;
import com.example.app.model.Bambino;
import com.example.app.model.Subscriber;
import com.example.app.model.Utente;
import com.example.app.service.BambinoService;
import com.example.app.service.UtenteService;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
@Slf4j
public class UtentiController {


    private final JsonParser jsonParser;

    private final UtenteService utenteService;

    private final BambinoService bambinoService;

    @Autowired
    private UtentiController(UtenteService utenteService, JsonParser jsonParser, BambinoService bambinoService) {
        this.utenteService = utenteService;
        this.jsonParser = jsonParser;
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

    @PostMapping("/form")
    public String submitForm(@Valid Subscriber subscriber, BindingResult bindingResult, Model m){
        if(bindingResult.hasErrors()){
            log.info("{}",bindingResult.getFieldErrors());
            String error ="";
            if (bindingResult.hasFieldErrors("name")) {
                error += "Campo nome errato ";
            }
            if (bindingResult.hasFieldErrors("surname")){
                error += "Campo cognome errato ";
            }
            if (bindingResult.hasFieldErrors("email")){
                error += "Email errata ";
            }
            m.addAttribute("message",error);
            return "subscription";
        }
       log.info("Successfully saved person: " + subscriber.toString());
        return "home";
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

    @GetMapping("/getUtentiBambini")
    @ResponseBody
    public List<UtenteBambino> getUtentiBambini() {
        return bambinoService.getUtenti();
    }

    @GetMapping("/token")
    @ResponseBody
    public String token() throws UnirestException, IOException {

       return jsonParser.tokenReader("admin", "admin");
    }


}
