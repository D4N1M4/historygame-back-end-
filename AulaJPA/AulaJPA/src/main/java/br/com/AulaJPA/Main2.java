package br.com.AulaJPA;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        LugarDAO lugarDAO = new LugarDAO();

        Lugar lugar = new Lugar();
        lugar.setNome("Praia do Forte");
        lugar.setDescricao("Uma das praias mais conhecidas.");

        Categoria categoria = new Categoria();
        categoria.setNome("Praia");
        lugar.setCategoria(categoria);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Águas");
        endereco.setCidade("Salvador");
        lugar.setEndereco(endereco);  

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setNota(4);
        avaliacao1.setComentario("Muito bonita!");

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setNota(5);
        avaliacao2.setComentario("Excelente local!");

        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.add(avaliacao1);
        avaliacoes.add(avaliacao2);
        lugar.setAvaliacoes(avaliacoes);

        Imagem imagem1 = new Imagem();
        imagem1.setUrl("http://imagem1.jpg");

        Imagem imagem2 = new Imagem();
        imagem2.setUrl("http://imagem2.jpg");

        List<Imagem> imagens = new ArrayList<>();
        imagens.add(imagem1);
        imagens.add(imagem2);
        lugar.setImagens(imagens);

        lugarDAO.salvar(lugar);

        List<Lugar> lugares = lugarDAO.listar();
        for (Lugar lugar1 : lugares) {
            System.out.println(lugar1.getNome());
        }

        Lugar lugarBuscado = lugarDAO.buscarPorId(1L);
        System.out.println("Lugar encontrado: " + lugarBuscado.getNome());
    }
}
