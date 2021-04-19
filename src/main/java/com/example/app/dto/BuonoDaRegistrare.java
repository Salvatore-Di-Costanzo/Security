package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuonoDaRegistrare {

    private int numeroBuono;

    private String nomeUtente;

    private String tipologia;
}
