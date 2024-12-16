package br.com.AulaJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class ImagemDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-basic");
    EntityManager em = emf.createEntityManager();
    
    public void salvar(Imagem imagem) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(imagem); 
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

    public Imagem buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Imagem imagem = em.find(Imagem.class, id);
        em.close();
        return imagem;
    }

    public List<Imagem> listar() {
        EntityManager em = emf.createEntityManager();
        List<Imagem> imagens = em.createQuery("FROM Imagem", Imagem.class).getResultList();
        em.close();
        return imagens;
    }

    public void atualizar(Imagem imagem) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(imagem);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Imagem imagem = em.find(Imagem.class, id);
        if (imagem != null) {
            em.remove(imagem);
        }
        em.getTransaction().commit();
        em.close();
    }
}
