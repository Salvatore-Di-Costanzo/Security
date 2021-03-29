package com.example.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class Subscriber {
    @Max(16)
    private String CF;

    @Size(min=2, max=30)
    @NotNull
    private String nome;

    @Size(min=2, max=30)
    @NotNull
    private String cognome;

    @NotNull
    private Date dataNascita;

    @NotNull
    private String luogoNascita;

    @NotNull
    private String provinciaNascita;

    @NotEmpty
    @Email
    private String email;
}
