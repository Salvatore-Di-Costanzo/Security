package com.example.app.repository;

import com.example.app.model.Sequenziale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenzialeRepository extends JpaRepository<Sequenziale, Integer> {

    Sequenziale findById(int id);
}
