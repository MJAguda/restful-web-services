package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource{
    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }
    
    //  GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

     //  GET /users
    @GetMapping("/users/{id}")
    public User retrieveUsers(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id:" + id);
        
            return user;
    }

    // DEL /users/
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }

    // POS /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);
        // return uri of the created user as part of the response
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}") //Append the userId to the path "/users"
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        
        // Change Status Response
        return ResponseEntity.created(location).build();
    }
}