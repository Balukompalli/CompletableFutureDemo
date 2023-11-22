package com.multithread.CompletableFutureDemo.service;

import com.multithread.CompletableFutureDemo.entity.User;
import com.multithread.CompletableFutureDemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    Object target;

    @Async
    public CompletableFuture<List<User>> saveUsers(MultipartFile file) {
        long start =  System.currentTimeMillis();
        List<User> userList =  parseCsv(file);
        log.info("save the list of users of size {} , thread name is : {}",userList.size(),Thread.currentThread().getName());
        userList = userRepository.saveAll(userList);
        long end =  System.currentTimeMillis();
        log.info("Total time : {}",(end-start));
        return CompletableFuture.completedFuture(userList);
    }


    @Async
    public CompletableFuture<List<User>> findAllUsers() {
    log.info("get list of users by :{}",Thread.currentThread().getName());
        List<User> userList = userRepository.findAll();
        return CompletableFuture.completedFuture(userList);
    }

    private List<User>  parseCsv(final MultipartFile multipartFile) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while((line= bufferedReader.readLine()) != null) {
                      final String[] data =  line.split(",");
                      User user = User.builder()
                              .name(data[0])
                              .email(data[1])
                              .gender(data[2])
                              .build();
                        userList.add(user);
                }
        return  userList;
        } catch (IOException e) {
            log.error("Failed to load CSV file {}", e);
            throw new RuntimeException(e);
        }
    }

}
