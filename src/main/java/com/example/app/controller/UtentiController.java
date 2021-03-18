package com.example.app.controller;

import com.example.app.model.Utenti;
import com.example.app.service.UtentiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class UtentiController {

    private final UtentiService utentiService;

    @Autowired
    private UtentiController ( UtentiService utentiService){
        this.utentiService = utentiService;
    }


    @GetMapping("/index")
    // in alternativa al required = false possiamo fare
    // @RequestParam Optional<String> name
    public String home(@RequestParam(required=false) String name ,Model model){
        model.addAttribute("name",name);
        return "home";
    }

    @GetMapping("/utenti")
    @ResponseBody
    public List<Utenti> getAllUtenti () {
        return utentiService.getUtenti();
    }
}
