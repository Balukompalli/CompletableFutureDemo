package com.multithread.CompletableFutureDemo.controller;

import com.multithread.CompletableFutureDemo.entity.User;
import com.multithread.CompletableFutureDemo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveUsers", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = "application/json")
    public ResponseEntity<User> saveUsers(@RequestParam(value = "files") MultipartFile[] multipartFiles) {

        for(MultipartFile multipartFile : multipartFiles) {
            userService.saveUsers(multipartFile);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(value = "/getUsers", produces = "application/json")
    public CompletableFuture<ResponseEntity> getAllUsers() {
        return userService.findAllUsers().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/getUsersByThread", produces = "application/json")
    public ResponseEntity getUsers() {
        CompletableFuture<List<User>> userList1 = userService.findAllUsers();
        CompletableFuture<List<User>> userList2 = userService.findAllUsers();
        CompletableFuture<List<User>> userList3 = userService.findAllUsers();

CompletableFuture.allOf(userList1,userList2,userList3).join();
return ResponseEntity.status(HttpStatus.OK).build();
    }
}
