package br.ufc.qx404library.service;
import br.ufc.qx404library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class CatalogService {
    private List<Book> catalogo = new ArrayList<>();

    public void AddLivro(Book livro){
        catalogo.add(livro);
        notificarNovoLivro(livro.getTitle());
        System.out.println("ok, livro cadastrado");
    }

    public List<Book> ListLivros(){
        return catalogo;
    }

    public Book buscarPorId(String id) {
        for (Book livro : catalogo) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;
    }

    public void notificarNovoLivro(String titulo) {
        try {
            java.net.DatagramSocket socket = new java.net.DatagramSocket();
            java.net.InetAddress grupo = java.net.InetAddress.getByName("230.0.0.1");
            int porta = 4446;

            String mensagem = "{ Novo Lançamento no Catálogo: " + titulo + " Está Disponível! }";
            byte[] buffer = mensagem.getBytes();

            java.net.DatagramPacket pacote = new java.net.DatagramPacket(buffer, buffer.length, grupo, porta);
            socket.send(pacote);
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro ao enviar multicast: " + e.getMessage());
        }
    }
}
