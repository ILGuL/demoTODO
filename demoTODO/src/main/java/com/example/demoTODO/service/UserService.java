package com.example.demoTODO.service;

import com.example.demoTODO.entity.UserEntity;
import com.example.demoTODO.exeption.UserAlreadyExistException;
import com.example.demoTODO.exeption.UserNotFoundException;
import com.example.demoTODO.model.User;
import com.example.demoTODO.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistException("User already exist");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();//getting a user from DB
        if (user == null ){
            throw new UserNotFoundException("User not found");
        }
        return User.toModel(user);
    }

    public Long delete(Long id){
        userRepo.deleteById(id);
        return id;
    }
}
