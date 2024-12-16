package br.com.AulaJPA;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        // Instância dos DAOs
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        LugarDAO lugarDAO = new LugarDAO();

        // Criar e salvar Categoria
        Categoria categoria = new Categoria();
        categoria.setNome("Praia");
        categoriaDAO.salvar(categoria);

        // Criar e salvar Endereço
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Águas");
        endereco.setCidade("Salvador");
        enderecoDAO.salvar(endereco);

        // Criar e salvar Lugar
        Lugar lugar = new Lugar();
        lugar.setNome("Praia do Forte");
        lugar.setDescricao("Uma das praias mais conhecidas.");
        lugar.setCategoria(categoria); // Relaciona com a Categoria salva
        lugar.setEndereco(endereco);  // Relaciona com o Endereço salvo

        // Adicionar Avaliações ao Lugar
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setNota(4);
        avaliacao1.setComentario("Muito bonita!");
        avaliacao1.setLugar(lugar);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setNota(5);
        avaliacao2.setComentario("Excelente local!");
        avaliacao2.setLugar(lugar);

        lugar.setAvaliacoes(new ArrayList<>());
        lugar.getAvaliacoes().add(avaliacao1);
        lugar.getAvaliacoes().add(avaliacao2);

        // Adicionar Imagens ao Lugar
        Imagem imagem1 = new Imagem();
        imagem1.setUrl("http://imagem1.jpg");
        imagem1.setLugar(lugar);

        Imagem imagem2 = new Imagem();
        imagem2.setUrl("http://imagem2.jpg");
        imagem2.setLugar(lugar);

        lugar.setImagens(new ArrayList<>());
        lugar.getImagens().add(imagem1);
        lugar.getImagens().add(imagem2);

        lugarDAO.salvar(lugar); // Salvar o Lugar com todas as associações

        // Listar todos os Lugares
        List<Lugar> lugares = lugarDAO.listar();
        for (Lugar l : lugares) {
            System.out.println(l.getNome());
        }

        // Buscar Lugar por ID
        Lugar lugarBuscado = lugarDAO.buscarPorId(1L);
        if (lugarBuscado != null) {
            System.out.println("Lugar encontrado: " + lugarBuscado.getNome());
            System.out.println("Descrição: " + lugarBuscado.getDescricao());
        } else {
            System.out.println("Lugar não encontrado com o ID especificado.");
        }
    }
}
