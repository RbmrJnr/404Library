package br.ufc.qx404library.network;
import java.awt.desktop.SystemSleepEvent;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8800); // Abrindo a porta 8800
            System.out.println("Aguardando Livros...");

            Socket socketCliente = serverSocket.accept();
            System.out.println("Conectado!");

            InputStream input = socketCliente.getInputStream(); // pegando os bytes para imprimir na tela
            int b;
            while ((b = input.read()) != -1){ // ler byter por byter convertendo para caractere
                System.out.print((char) b);
            }
            socketCliente.close();
            serverSocket.close();
            System.out.println("\nConexão encerrada.");
        }catch (Exception e){
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}
