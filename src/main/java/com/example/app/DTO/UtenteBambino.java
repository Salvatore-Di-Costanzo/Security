package com.example.app.DTO;

import com.example.app.model.Bambino;
import com.example.app.model.Utente;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class UtenteBambino {
    private List<Bambino> bambino;
    private List<Utente> utente;

}
