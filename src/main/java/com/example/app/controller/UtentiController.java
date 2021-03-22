package com.example.app.controller;

import com.example.app.configuration.JsonParser;
import com.example.app.model.Utenti;
import com.example.app.service.UtentiService;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
public class UtentiController {


    private final JsonParser jsonParser;

    private final UtentiService utentiService;

    @Autowired
    private UtentiController(UtentiService utentiService, JsonParser jsonParser) {
        this.utentiService = utentiService;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {

        request.logout();
        ModelAndView model = new ModelAndView("redirect:/index");
        model.addObject("name", "Pasquale");
        return model;
    }

    @GetMapping("/index")
    public String home(Model model, HttpServletRequest request) {

        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String name = session.getToken().getPreferredUsername();

        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/utenti")
    @ResponseBody
    public List<Utenti> getAllUtenti() {
        return utentiService.getUtenti();
    }

    @GetMapping("/token")
    @ResponseBody
    public String tokenEKTMOURTENN() throws UnirestException, IOException {


       return jsonParser.tokenReader("admin", "admin");
    }


}
