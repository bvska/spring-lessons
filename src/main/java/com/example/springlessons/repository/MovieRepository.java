package com.example.springlessons.repository;

import com.example.springlessons.entity.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository
        extends PagingAndSortingRepository<Movie, Integer> {
}
