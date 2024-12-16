package br.com.AulaJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-basic");
        EntityManager em = emf.createEntityManager();

        // Criar Categoria
        em.getTransaction().begin();
        Categoria categoria = new Categoria();
        categoria.setNome("Praia");
        em.persist(categoria);
        em.getTransaction().commit();

        // Criar Endereço
        em.getTransaction().begin();
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Águas");
        endereco.setCidade("Salvador");
        em.persist(endereco);
        em.getTransaction().commit();

        // Criar Lugar
        em.getTransaction().begin();
        Lugar lugar = new Lugar();
        lugar.setNome("Praia do Forte");
        lugar.setDescricao("Uma das praias mais conhecidas.");
        lugar.setCategoria(categoria);  // Associar Categoria
        lugar.setEndereco(endereco);    // Associar Endereço
        em.persist(lugar);
        em.getTransaction().commit();

        // Criar Avaliações
        em.getTransaction().begin();
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setNota(4);
        avaliacao1.setComentario("Muito bonita!");
        avaliacao1.setLugar(lugar);     // Associar Lugar
        em.persist(avaliacao1);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setNota(5);
        avaliacao2.setComentario("Excelente local!");
        avaliacao2.setLugar(lugar);     // Associar Lugar
        em.persist(avaliacao2);
        em.getTransaction().commit();

        // Criar Imagens
        em.getTransaction().begin();
        Imagem imagem1 = new Imagem();
        imagem1.setUrl("http://imagem1.jpg");
        imagem1.setLugar(lugar);       // Associar Lugar
        em.persist(imagem1);

        Imagem imagem2 = new Imagem();
        imagem2.setUrl("http://imagem2.jpg");
        imagem2.setLugar(lugar);       // Associar Lugar
        em.persist(imagem2);
        em.getTransaction().commit();

        // Buscar Lugar e exibir informações
        Lugar lugarBuscado = em.find(Lugar.class, lugar.getId());
        System.out.println("Lugar: " + lugarBuscado.getNome());
        System.out.println("Descrição: " + lugarBuscado.getDescricao());

        // Exibir Avaliações associadas
        System.out.println("Avaliações:");
        for (Avaliacao avaliacao : lugarBuscado.getAvaliacoes()) {
            System.out.println("- " + avaliacao.getComentario() + " (Nota: " + avaliacao.getNota() + ")");
        }

        // Exibir Imagens associadas
        System.out.println("Imagens:");
        for (Imagem imagem : lugarBuscado.getImagens()) {
            System.out.println("- " + imagem.getUrl());
        }

        em.close();
        emf.close();
    }
}
