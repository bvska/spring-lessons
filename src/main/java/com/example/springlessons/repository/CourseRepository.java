package com.example.springlessons.repository;

import com.example.springlessons.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    @Query(value = "SELECT c FROM Course c WHERE UPPER(c.title) LIKE %:courseTitle%") // nativeQuery = true
    Iterable<Course> findLikeTitle(@Param("courseTitle") String courseTitle);

    @Query(value = "SELECT c FROM Course c WHERE UPPER(c.title) = :courseTitle")
    Course findByTitle(@Param("courseTitle") String courseTitle); // Optional<Course>
}

// PagingAndSortingRepository
// CrudRepository