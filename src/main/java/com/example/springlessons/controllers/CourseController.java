package com.example.springlessons.controllers;

import com.example.springlessons.entity.Course;
import com.example.springlessons.exception.CourseException;
import com.example.springlessons.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

//http://localhost/courses/{id} GET
//http://localhost/courses GET
//http://localhost/courses POST
//http://localhost/courses PUT
//http://localhost/courses/{id} DELETE

// пример json от клиента: используйте двойные кавычки, после последней пары , не ставится
/* id указывать, если put запрос
{
    "id": 1,
    "title": "java",
    "duration": 7,
    "start": "2021-04-08",
    "price": 50000
}
*/

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping("/{id}")
    public Course getById(@PathVariable int id){
        Optional<Course> optionalCourse;
        try {
            optionalCourse = service.getById(id);
        } catch (CourseException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return optionalCourse.get();
    }

    @GetMapping // http://localhost/courses?page=5&size=10
    public Page<Course> getAll(@RequestParam int page, @RequestParam int size){
        Page<Course> coursePage;
        try {
            coursePage = service.getByPage(page, size);
        } catch (CourseException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return coursePage;
    }
//    @GetMapping
//    public Iterable<Course> getAll(){ }
    // controller -> service -> repository
    @PostMapping
    public Course addCourse(@RequestBody Course course){
        Course saved;
        try {
            saved = service.add(course);
        } catch (CourseException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
        return saved;
    }

    @PutMapping
    public Course updateCourse(@RequestBody Course course){
        Course updated;
        try {
            updated = service.update(course);
        } catch (CourseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        try {
            service.delete(id);
        } catch (CourseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
