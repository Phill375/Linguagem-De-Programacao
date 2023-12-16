package br.org.fundatec.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE , generator="restaurante_generator")
    @TableGenerator(name="restaurante_generator",
            table="chave",
            pkColumnName="id",
            valueColumnName="valor",
            allocationSize=100)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    public Restaurante() {
    }

    public Restaurante(String nome) {
        super();
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante escolha = (Restaurante) o;
        return id.equals(escolha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Nome: " + nome;
    }
}
