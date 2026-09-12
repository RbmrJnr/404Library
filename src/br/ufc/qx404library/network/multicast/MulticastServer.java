package br.ufc.qx404library.network.multicast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress grupo = InetAddress.getByName("230.0.0.1");
            int porta = 4446;

            String mensagem = "{ \"tipo\": \"NOTIFICACAO\", \"mensagem\": \"Clube de Leitura Hoje Ás 20H\", \"timestamp\": " + System.currentTimeMillis() + " }";
            byte[] buffer = mensagem.getBytes();

            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, grupo, porta);
            socket.send(pacote);

            System.out.println("Mensagem multicast enviada!");
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro no servidor multicast: " + e.getMessage());
        }
    }
}
