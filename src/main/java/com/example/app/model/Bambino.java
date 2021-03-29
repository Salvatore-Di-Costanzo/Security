package com.example.app.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="bambino")
@Data
@NoArgsConstructor
public class Bambino {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="id")
    private int id;

    @NotNull
    @Min(2)
    @Column(name ="nomebambino")
    private String nomeBambino;

    @NotNull
    @Min(2)
    @Column(name ="cognomebambino")
    private String cognomeBambino;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="email", nullable=false)
    private Utente utente;

}
