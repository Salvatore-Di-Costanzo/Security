package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "buono")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buono {

    @Id
    @Column(name="id")
    private int id;

    @Column(name ="beneficiario")
    private String beneficiario;

    @Column(name="tipologia")
    private String tipologia;

}
