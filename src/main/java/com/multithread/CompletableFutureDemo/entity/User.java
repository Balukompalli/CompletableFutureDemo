package com.multithread.CompletableFutureDemo.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name= "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private  String name;

    @Column(name = "email")
    private  String email;

    @Column(name = "gender")
    private  String gender;

}
