package com.example.app.controller;

import com.example.app.service.BambinoService;
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

    private final BambinoService bambinoService;

    @Autowired
    private PageController(UtenteService utenteService, BambinoService bambinoService) {
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
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String name = session.getToken().getPreferredUsername();
        return "home";
    }

    @RequestMapping(path = "/ricercaBambini", method = RequestMethod.GET)
    public String utenti(Model model){
        model.addAttribute("Bambini", bambinoService.getAllUtenti());
        return "bambini";
    }


    @GetMapping("/sub")
    public String subscription(Model m) {
        return "subscription";
    }
}
