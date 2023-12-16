package br.org.fundatec.repository;

import br.org.fundatec.model.Restaurante;

import javax.persistence.*;
import java.util.List;

public class RestauranteRepository {

    private EntityManager em;

    public RestauranteRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("VotacaoDB");
        em = factory.createEntityManager();
    }

    public void inserir(Restaurante restaurante) {
        this.em.getTransaction().begin();
        this.em.persist(restaurante);
        this.em.getTransaction().commit();
    }

    public List<Restaurante> listarRestaurantes() {
        TypedQuery<Restaurante> query = em.createQuery("SELECT r FROM Restaurante r", Restaurante.class);
        return query.getResultList();
    }

    public Restaurante buscarPorNome(String nome) {
        TypedQuery<Restaurante> query = this.em.createQuery("SELECT r FROM Restaurante r WHERE r.nome = :nome", Restaurante.class);
        query.setParameter("nome", nome);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
