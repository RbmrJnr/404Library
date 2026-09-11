package br.ufc.qx404library.network;
import br.ufc.qx404library.io.BookInputStream;
import br.ufc.qx404library.model.Book;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerInput{
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(8800); // Definindo porta
            System.out.println("Aguardando Livros...");

            Socket socket = serverSocket.accept(); // Esperando Conexão
            System.out.println("Conectado!");

            BookInputStream bis = new BookInputStream(socket.getInputStream()); // Pega os bytes pelo getInputStream e transforma em texto
            List<Book> livros = bis.CadastrarLivros();

            System.out.println("\nNovos Livros recebidos para Cadastro! Total: " + livros.size());
            for (Book livro : livros) {
                System.out.println(" -> ID: " + livro.getId() + " | Título: " + livro.getTitle() + " - Autor: " + livro.getAuthor());
            }

            socket.close();
            serverSocket.close();
            System.out.println("\nConexão Encerrada.");

        }catch (Exception e){
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}
