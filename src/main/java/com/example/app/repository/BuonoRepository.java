package com.example.app.repository;

import com.example.app.model.Buono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.server.PathParam;

public interface BuonoRepository extends JpaRepository<Buono,Integer> {

    @Transactional
    @Modifying
    @Query("delete from Buono b where b.id=:id")
    void deleteByid(@PathParam("id") Integer id);

}
