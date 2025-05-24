package br.com.ifpe.historygame.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter @Setter
public class Usuario {
    
    @Id
    private String uid;
    
    private String nome;
    private String email;
    @Column(nullable = true)
    private String senha; // Se tiver autenticação

    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Desejado> desejados;

    @OneToMany(mappedBy = "usuario")
    private List<Jogado> jogados;
}
