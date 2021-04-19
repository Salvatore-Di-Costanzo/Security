package com.example.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sequenziale")
@Data
public class Sequenziale {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "numero")
    private int numero;
}
