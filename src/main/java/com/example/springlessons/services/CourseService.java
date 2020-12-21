package com.example.springlessons.services;

import com.example.springlessons.entity.Course;
import com.example.springlessons.exception.CourseException;
import com.example.springlessons.repository.CourseRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    @Getter
    private CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course add(Course course) {
        if (repository.existsById(course.getId()) ||
            repository.findByTitle(course.getTitle().toUpperCase()) != null) {
            throw new CourseException("Запись уже существует");
        }
        return repository.save(course);
    }

    public Course update(Course course){
        if (!repository.existsById(course.getId())) {
            throw new CourseException("Запись не существует");
        }
        return repository.save(course);
    }

    public Page<Course> getByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursesPage = repository.findAll(pageable);
        if (coursesPage.getContent().isEmpty()) {
            throw new CourseException("Записи не были найдены");
        }
        return coursesPage;
    }

    public Optional<Course> getById(int id) {
        Optional<Course> optionalCourse = repository.findById(id);
        if (optionalCourse.isEmpty()) throw new CourseException("Запись не существует");
        return optionalCourse;
    }

    public void delete(int id){
        if (!repository.existsById(id)) throw new CourseException("Запись не существует");
        repository.deleteById(id);
    }
}

