package br.com.AulaJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class ContatoDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-basic");
    EntityManager em = emf.createEntityManager();
    
    public void salvar(Contato contato) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(contato); 
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
    public Contato buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Contato contato = em.find(Contato.class, id);
        em.close();
        return contato;
    }

    public List<Contato> listar() {
        EntityManager em = emf.createEntityManager();
        List<Contato> contatos = em.createQuery("FROM Contato", Contato.class).getResultList();
        em.close();
        return contatos;
    }

    public void atualizar(Contato contato) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(contato);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Contato contato = em.find(Contato.class, id);
        if (contato != null) {
            em.remove(contato);
        }
        em.getTransaction().commit();
        em.close();
    }
}
