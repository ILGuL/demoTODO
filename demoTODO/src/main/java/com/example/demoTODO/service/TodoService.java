package com.example.demoTODO.service;

import com.example.demoTODO.entity.TodoEntity;
import com.example.demoTODO.entity.UserEntity;
import com.example.demoTODO.model.Todo;
import com.example.demoTODO.repository.TodoRepo;
import com.example.demoTODO.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private UserRepo userRepo; // serve to find user for indicating him in entity

    @Autowired //injection of repository TodoRepo
    private TodoRepo todoRepo;

    public Todo createTodo(TodoEntity todo, Long userId){
        UserEntity user = userRepo.findById(userId).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo completeTodo(Long id){
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}
