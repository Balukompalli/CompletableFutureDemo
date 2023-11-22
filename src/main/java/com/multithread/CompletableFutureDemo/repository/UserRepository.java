package com.multithread.CompletableFutureDemo.repository;

import com.multithread.CompletableFutureDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
