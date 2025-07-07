package com.example.msauth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    @Builder.Default
    private Roles rol = Roles.CLIENTE;
    private String password;
    private Integer clienteId;
    private Integer vendedorId;

    public enum Roles {
        CLIENTE,
        VENDEDOR
    }
}
