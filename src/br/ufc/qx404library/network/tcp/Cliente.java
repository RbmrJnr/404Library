package br.ufc.qx404library.network.tcp;

import br.ufc.qx404library.model.Book;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8800);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            Book livro = new Book("10", "Dom Casmurro", "Machado de Assis");
            System.out.println("Enviando livro serializado: " + livro.getTitle());

            oos.writeObject(livro); // empacotando e enviando
            oos.flush();

            oos.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}
