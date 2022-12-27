package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="alvara")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Alvara {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "nome obrigatorio")
    @Size(min = 2, max = 30, message = "Deve ter entre {min} e {max} caracteres")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "orgao", nullable = false)
    private String orgao;

    @Column(name = "valido", nullable = false)
    private String valido;
}
