package com.example.springlessons.repository;

import com.example.springlessons.entity.Actor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ActorRepository
        extends PagingAndSortingRepository<Actor, Integer> {

    @Query("SELECT a FROM Actor a WHERE a.name = :name")
    Optional<Actor> getByName(@Param("name") String name);
}
// movies/search
// movies GET
// movies/{id} GET
// movies?page=0&size=30 GET
// movies POST
// movies PUT
