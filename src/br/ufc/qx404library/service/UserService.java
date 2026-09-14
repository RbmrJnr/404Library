package br.ufc.qx404library.service;
import br.ufc.qx404library.model.Book;
import br.ufc.qx404library.model.User;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> usuarios = new ArrayList<>();

    public void cadastrarUsuario(User usuario) {
        for (User u : usuarios) {
            if (u.getId().equals(usuario.getId())) {
                System.out.println("Já existe um usuário cadastrado com o ID " + usuario.getId());
                return;
            }
        }

        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso: " + usuario.getUsername());
    }

    public User buscarPorId(String id) {
        for (User u : usuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        System.out.println("ID de Usuário" + id + " não encontrado.");
        return null;
    }

    public List<User> listarUsuarios() {
        return usuarios;
    }

    public void emprestarLivro(String idUsuario, String idLivro, CatalogService catalogo) {
        User usuario = buscarPorId(idUsuario);
        Book livro = catalogo.buscarPorId(idLivro);

        if (usuario == null) {
            System.out.println("Usuário não encontrado no sistema.");
            return;
        }
        if (livro == null) {
            System.out.println("Livro não disponível no catálogo.");
            return;
        }
        System.out.println("O usuário " + usuario.getUsername() + " pegou o livro " + livro.getTitle() + " emprestado.");
        notificarEmprestimoMulticast(livro.getTitle());
    }

    private void notificarEmprestimoMulticast(String tituloLivro) {
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress grupo = InetAddress.getByName("230.0.0.1");
            int porta = 4446;
            String mensagem = "{ O seguinte Livro: " + tituloLivro + " acabou de ser alugado e não está mais disponível! }";
            byte[] buffer = mensagem.getBytes();
            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, grupo, porta);
            socket.send(pacote);
            socket.close();

        }catch (Exception e){
            System.out.println("Erro ao enviar notificação multicast: " + e.getMessage());
        }
    }
}
