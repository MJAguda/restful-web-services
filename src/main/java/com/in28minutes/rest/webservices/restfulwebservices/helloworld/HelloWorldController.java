package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//  REST API
@RestController
public class HelloWorldController {
    
    // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String hellowWord(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean hellowWordBean(){
        return new HelloWorldBean("Hello World");
    }

    // Path Parameter
    // /users/{id}/todos/{id} => /users/1/todos/101
    // /hello-world/path-variable/{name}

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean hellowWordPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World %s", name));
    }


}
