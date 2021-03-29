package com.example.app.repository;

import com.example.app.model.Utente;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Configuration
public interface UtenteRepository extends JpaRepository<Utente, String> {

    List<Utente> findAll();

    @Query("Select u.punteggio from Utente u where u.email=:email")
    int punteggioUtente(@Param("email") String email);

    @Query("update Utente u set u.punteggio = :punteggio WHERE u.email = :email")
    @Transactional
    @Modifying
    void setPunteggioUtente(@Param("email") String email, @Param("punteggio") int punteggio);

    Utente findUtentiByNome(String nome);
}
