package br.ufc.qx404library.network.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastClient {
    public static void main(String[] args) {
        try {
            InetAddress grupo = InetAddress.getByName("230.0.0.1");
            int porta = 4446;

            MulticastSocket socket = new MulticastSocket(porta);
            socket.joinGroup(grupo);

            System.out.println("Cliente entrou no grupo multicast...");

            // Thread para escutar mensagens
            Thread escuta = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                        socket.receive(pacote);
                        String mensagem = new String(pacote.getData(), 0, pacote.getLength());
                        System.out.println("\nMensagem recebida: " + mensagem);
                    }
                } catch (Exception e) {
                    System.out.println("Erro na escuta: " + e.getMessage());
                }
            });

            escuta.start();

            // Thread para interação do usuário
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Digite algo (ou 'sair' para encerrar): ");
                String entrada = scanner.nextLine();
                if (entrada.equalsIgnoreCase("sair")) {
                    break;
                }
                System.out.println("Você digitou: " + entrada);
            }

            socket.leaveGroup(grupo);
            socket.close();
            System.out.println("Cliente saiu do grupo multicast.");
        } catch (Exception e) {
            System.out.println("Erro no cliente multicast: " + e.getMessage());
        }
    }
}
