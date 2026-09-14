package br.ufc.qx404library.network.ServiceServer;
import br.ufc.qx404library.io.BookInputStream;
import br.ufc.qx404library.model.Book;
import br.ufc.qx404library.model.User;
import br.ufc.qx404library.service.CatalogService;
import br.ufc.qx404library.service.UserService;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        CatalogService catalogo = new CatalogService();
        UserService userService = new UserService();
        User user = new User("1", "Ribamar", "888888");
        User user1 = new User("2", "Cintia", "999999");
        User user2 = new User("1", "Copião", "324959");
        userService.cadastrarUsuario(user);
        userService.cadastrarUsuario(user1);
        userService.cadastrarUsuario(user2);
        try{
            ServerSocket serverSocket = new ServerSocket(8808);
            System.out.println("Aguardando Conexão...");
            Socket socket = serverSocket.accept();
            System.out.println("ok");

            BookInputStream bis = new BookInputStream(socket.getInputStream());
            List<Book> livrosRecebidos = bis.CadastrarLivros();
            for (Book livro : livrosRecebidos) {
                catalogo.AddLivro(livro);
            }

            if (!livrosRecebidos.isEmpty()) {
                System.out.println("\nSimulação de empréstimos: \n");
                String idLivroRibamar = livrosRecebidos.get(0).getId();
                userService.emprestarLivro("1", idLivroRibamar, catalogo);
                if (livrosRecebidos.size() > 1) {
                    String idLivroCintia = livrosRecebidos.get(1).getId();
                    userService.emprestarLivro("2", idLivroCintia, catalogo);
                } else {
                    System.out.println("Não tem mais livros no catalogo, a biblioteca faliu");
                }
            }

            socket.close();
            serverSocket.close();
            System.out.println("Operações finalizadas.");

        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}