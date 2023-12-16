package br.org.fundatec.repository;

import br.org.fundatec.model.Voto;

import javax.persistence.*;
import java.util.List;

public class VotoRepository {
    private EntityManager em;

    public VotoRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("VotacaoDB");
        em = factory.createEntityManager();
    }

    public void inserir(Voto voto) {
        this.em.getTransaction().begin();
        this.em.persist(voto);
        this.em.getTransaction().commit();
    }

    public List<Voto> buscar() {
        TypedQuery<Voto> buscarTodosQuery = this.em.createQuery("select e from Voto e", Voto.class);
        return buscarTodosQuery.getResultList();
    }

    public Voto buscar(Integer id) {
        return  this.em.find(Voto.class, id);
    }

    public void atualizar(Voto voto) {
        this.em.merge(voto);
    }

    public void remove(Voto voto) {
        this.em.remove(voto);
    }

    public Voto buscarPorVoto(Integer voto) {
        TypedQuery<Voto> query = this.em.createQuery("select e from Voto e where e.votos = :voto", Voto.class);
        query.setParameter("voto", voto);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
