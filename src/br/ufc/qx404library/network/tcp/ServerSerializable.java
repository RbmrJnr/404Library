package br.ufc.qx404library.network.tcp;

import br.ufc.qx404library.model.Book;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSerializable {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8800);
            System.out.println("Servidor aguardando objeto...");

            Socket socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Book livroRecebido = (Book) ois.readObject(); // desempacotando
            System.out.println("Livro recebido: " + livroRecebido.getId() + " - " 
                               + livroRecebido.getTitle() + " (" + livroRecebido.getAuthor() + ")");

            ois.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}
