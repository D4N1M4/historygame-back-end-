package br.com.AulaJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class LugarDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-basic");
    EntityManager em = emf.createEntityManager();
    
    public void salvar(Lugar lugar) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(lugar);
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

    public Lugar buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Lugar lugar = em.find(Lugar.class, id);
        em.close();
        return lugar;
    }

    public List<Lugar> listar() {
        EntityManager em = emf.createEntityManager();
        List<Lugar> lugares = em.createQuery("FROM Lugar", Lugar.class).getResultList();
        em.close();
        return lugares;
    }

    public void atualizar(Lugar lugar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(lugar);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Lugar lugar = em.find(Lugar.class, id);
        if (lugar != null) {
            em.remove(lugar);
        }
        em.getTransaction().commit();
        em.close();
    }
}

