package com.lambdaschool.anywherefitness.controllers;

import com.lambdaschool.anywherefitness.models.Todos;
import com.lambdaschool.anywherefitness.models.User;
import com.lambdaschool.anywherefitness.repository.UserRepository;
import com.lambdaschool.anywherefitness.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/todos")
public class TodosController
{
    @Autowired
    private TodosService todosService;

    @Autowired
    private UserRepository usersrepo;

    @PostMapping(value = "/user/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addNewTodo(@Valid @RequestBody Todos newtodo, @PathVariable long userid) throws URISyntaxException
    {
        User user = usersrepo.findById(userid).orElseThrow(() -> new EntityNotFoundException("User " + userid + " Not Found! "));
        newtodo.setTodoid(0);
        newtodo.setUser(user);
        todosService.save(newtodo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTodosURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/todoid}")
                .buildAndExpand(newtodo.getTodoid())
                .toUri();
        responseHeaders.setLocation(newTodosURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/todo/{todoid}")
    public ResponseEntity<?> updateTodo(@PathVariable long todoid)
    {
        todosService.update(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/todo/{todoid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long todoid)
    {
        todosService.delete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
