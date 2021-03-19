package com.example.app.controller;

import com.example.app.configuration.JsonParser;
import com.example.app.model.TokenJson;
import com.example.app.model.Utenti;
import com.example.app.service.UtentiService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.Token;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
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
    public String home(@RequestParam(required = false) String name, Model model, HttpServletRequest request) {


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
