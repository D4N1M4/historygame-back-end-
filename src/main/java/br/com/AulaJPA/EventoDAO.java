package br.com.AulaJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class EventoDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-basic");
    EntityManager em = emf.createEntityManager();
    
    public void salvar(Evento evento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(evento); 
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

    public Evento buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Evento evento = em.find(Evento.class, id);
        em.close();
        return evento;
    }

    public List<Evento> listar() {
        EntityManager em = emf.createEntityManager();
        List<Evento> eventos = em.createQuery("FROM Evento", Evento.class).getResultList();
        em.close();
        return eventos;
    }

    public void atualizar(Evento evento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(evento);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Evento evento = em.find(Evento.class, id);
        if (evento != null) {
            em.remove(evento);
        }
        em.getTransaction().commit();
        em.close();
    }
}
