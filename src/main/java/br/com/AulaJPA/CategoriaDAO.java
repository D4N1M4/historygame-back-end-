package br.com.AulaJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class CategoriaDAO {
   	EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-basic");
    EntityManager em = emf.createEntityManager();
    
    public void salvar(Categoria categoria) {
        EntityManager em = emf.createEntityManager();

         try {
            em.getTransaction().begin();
            em.persist(categoria); 
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

    public Categoria buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public List<Categoria> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Categoria", Categoria.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Categoria categoria) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Usar merge para garantir que a categoria é atualizada ou salva, caso seja novo
            em.merge(categoria);
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
        try {
            em.getTransaction().begin();
            Categoria categoria = em.find(Categoria.class, id);
            if (categoria != null) {
                em.remove(categoria);
            }
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
}
