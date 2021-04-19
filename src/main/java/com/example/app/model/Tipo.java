package com.example.app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tipo")
@Data
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="tipo")
    private String tipo;
}
