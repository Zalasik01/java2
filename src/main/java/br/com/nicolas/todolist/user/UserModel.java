package br.com.nicolas.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name ="tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(name = "usuario", unique = true)
    private String username;
    
    @Column(name = "nome")
    private String name;

    @Column(name = "senha")
    private String password;

    @CreationTimestamp
    @Column(name = "data-de-criacao")
    private LocalDateTime createdAt;

}
