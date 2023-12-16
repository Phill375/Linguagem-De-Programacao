package br.org.fundatec.repository;

import br.org.fundatec.model.Funcionario;

import javax.persistence.*;
import java.util.List;

public class FuncionarioRepository {

    private EntityManager em;

    public FuncionarioRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("VotacaoDB");
        em = factory.createEntityManager();
    }

    public void inserir(Funcionario funcionario) {
        this.em.getTransaction().begin();
        this.em.merge(funcionario);
        this.em.getTransaction().commit();
    }

    public List<Funcionario> listarFuncionarios() {
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        return query.getResultList();
    }


    public Funcionario buscar(Integer id) {
        return  this.em.find(Funcionario.class, id);
    }

    public void atualizar(Funcionario funcionario) {
        this.em.merge(funcionario);
    }

    public void remove(Funcionario funcionario) {
        this.em.remove(funcionario);
    }

    public Funcionario buscarPorNome(String nome){
        TypedQuery<Funcionario> query = this.em.createQuery("select e from Funcionario e where lower(e.nome) = lower(:nome)", Funcionario.class);
        query.setParameter("nome", nome);

        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

}
