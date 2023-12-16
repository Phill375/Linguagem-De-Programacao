package br.org.fundatec.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE , generator="funcionario_generator")
    @TableGenerator(name="funcionario_generator",
            table="chave",
            pkColumnName="id",
            valueColumnName="valor",
            allocationSize=100)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "funcionario")
    private List<Voto> votos;

    public Funcionario() {
    }

    public Funcionario(String nome) {
        super();
        this.nome = nome;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
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
        Funcionario colaborador = (Funcionario) o;
        return Objects.equals(id, colaborador.id);
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public void add(Voto voto) {
        if (voto != null) {
            if (votos == null) {
                votos = new ArrayList<>();
            }
            voto.setFuncionario(this);
            votos.add(voto);
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Nome: " + nome + ", Votos: " + votos;
    }
}
