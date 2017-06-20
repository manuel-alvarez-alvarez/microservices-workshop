package es.malvarez.microservices.collision.controller;


import es.malvarez.microservices.collision.domain.Collision;
import es.malvarez.microservices.collision.service.CollisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * So this is our API inside a Monolith
 */
@Controller
@RequestMapping(value = "/api")
public class CollisionController {

    private final CollisionService collisionService;

    @Autowired
    public CollisionController(final CollisionService collisionService) {
        this.collisionService = collisionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Collision> findAll() {
        return collisionService.findAll();
    }

    @GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Collision> findLast() {
        return collisionService.findLast();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Collision find(@PathVariable final UUID id) {
        return collisionService.find(id);
    }
}
