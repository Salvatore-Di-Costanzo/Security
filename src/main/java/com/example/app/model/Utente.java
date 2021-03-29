package com.example.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="utente")
public class Utente {

    @Column(name ="email")
    @Id
    @Email
    private String email;

    @Column(name ="cf")
    @Pattern(regexp = "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$\n", message = "Codice Fiscale non valido")
    @NotNull
    private String CF;

    @Column(name = "nome")
    @NotNull
    @Min(2)
    private String nome;

    @Column(name = "cognome")
    @NotNull
    @Min(2)
    private String cognome;

    @Column(name ="datanascita")
    @NotNull
    private LocalDate dataNascita;

    @Column(name ="luogonascita")
    @NotNull
    @Pattern(regexp = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", message = "Formato data nascita non valido")
    private String luogoNascita;

    @Column(name="cellulare")
    private String cellulare;

    @Column(name="punteggio")
    private int punteggio;

    @JsonManagedReference
    @OneToMany(mappedBy = "utente",cascade = {CascadeType.ALL})
    private List<Bambino> bambini;
}
