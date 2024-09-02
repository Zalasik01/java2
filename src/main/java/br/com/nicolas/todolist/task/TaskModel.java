package br.com.nicolas.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "tb_task")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "descricao")
    private String description;

    @Column(name = "titulo", length = 50)
    private String title;

    @Column(name = "inicio")
    private LocalDateTime startAt;

    @Column(name = "fim")
    private LocalDateTime endAt;

    @Column(name = "prioridade")
    private String priority;

    @Column(name = "id_usuario")
    private UUID idUser;

    @Column(name = "usuario")
    private String username;

    @CreationTimestamp
    @Column(name = "criacao")
    private LocalDateTime creatAt;    

    public void setTitle(String title) throws Exception{
        if(title.length() > 50) {
            throw new Exception("Erro: O título excede o limite permitido de 50 caracteres. Tamanho atual: " + title.length() + " caracteres.");            
        }
        this.title = title;
    }
}