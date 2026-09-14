package br.ufc.qx404library;
import br.ufc.qx404library.model.Book;
import br.ufc.qx404library.io.BookOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Book b1 = new Book("1", "O Chamado de Cthulhu", "H.P. Lovecraft");
        Book b2 = new Book("2", "Harry Potter", "J.K. Rowling");
        Book b3 = new Book("3", "One Piece", "Oda");
        Book b4 = new Book("4", "JoJo Bizarre Adventures", "Araki");
        Book[] LivrosBiblioteca = {b1, b2, b3, b4};


        System.out.println("System.Out:");
        BookOutputStream bosConsole = new BookOutputStream(System.out, LivrosBiblioteca, 4);
        bosConsole.enviarLivros();

        System.out.println("FileOutputStream:");
        FileOutputStream fos = new FileOutputStream("livros.txt");
        BookOutputStream bosArquivo = new BookOutputStream(fos, LivrosBiblioteca, 4);
        bosArquivo.enviarLivros();
        System.out.println("O Arquivo livros.txt foi gerado!");

        System.out.println("TCP:");
        Socket socket = new Socket("127.0.0.1", 8808);
        BookOutputStream bosTCP = new BookOutputStream(socket.getOutputStream(), LivrosBiblioteca, 4);
        bosTCP.enviarLivros();
        socket.close();
        System.out.println("Livros enviados!");
    }
}
