package com.example.demoTODO.controller;

import com.example.demoTODO.entity.UserEntity;
import com.example.demoTODO.exeption.UserAlreadyExistException;
import com.example.demoTODO.exeption.UserNotFoundException;
import com.example.demoTODO.repository.UserRepo;
import com.example.demoTODO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Spring know this class is a controller
@RequestMapping("/users") // Requests that should be processed by this controller should start with url "users"
public class UserController {

    @Autowired //service implementation
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User was saved successfully"); //return code 200
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error has occurred");//return code 400 with message
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id)); //return code 200
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());//return code 400 with message
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error has occurred");//return code 400 with message
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id)); //return code 200
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error has occurred");//return code 400 with message
        }
    }
}
