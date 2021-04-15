package com.example.app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="negozio")
@Data
public class Negozio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nomenegozio")
    private String nomeNegozio;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "email")
    private String email;
}
