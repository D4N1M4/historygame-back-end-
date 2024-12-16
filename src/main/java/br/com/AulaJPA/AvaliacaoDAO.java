package br.com.AulaJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class AvaliacaoDAO {
   	EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-basic");
    EntityManager em = emf.createEntityManager();  
    
    public void salvar(Avaliacao avaliacao) {
        EntityManager em = emf.createEntityManager();      
        try {
            em.getTransaction().begin();
            em.persist(avaliacao); 
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Avaliacao buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Avaliacao avaliacao = em.find(Avaliacao.class, id);
        em.close();
        return avaliacao;
    }

    public List<Avaliacao> listar() {
        EntityManager em = emf.createEntityManager();
        List<Avaliacao> avaliacoes = em.createQuery("FROM Avaliacao", Avaliacao.class).getResultList();
        em.close();
        return avaliacoes;
    }

    public void atualizar(Avaliacao avaliacao) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(avaliacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Avaliacao avaliacao = em.find(Avaliacao.class, id);
        if (avaliacao != null) {
            em.remove(avaliacao);
        }
        em.getTransaction().commit();
        em.close();
    }
}
