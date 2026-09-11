package br.ufc.qx404library;

import br.ufc.qx404library.io.BookInputStream;
import br.ufc.qx404library.model.Book;
import java.io.FileInputStream;
import java.util.List;

public class MainInput{
    public static void main(String[] args){
        try{
            System.out.println("System.in:");
            System.out.println("Use o Formato <id: titulo - autor>");
            System.out.println("Pressione ENTER após cada livro. pressione Ctrl+D para encerrar o programa:\n");

            // Definindo o teclado como origem das informações
            BookInputStream bisConsole = new BookInputStream(System.in);
            List<Book> livros = bisConsole.CadastrarLivros();

            System.out.println("\n\nCadastro Finalizado! " + livros.size() + " livros foram cadastrados");

            // Imprime os livros recebidos bytes por byte
            for(Book livro : livros){
                System.out.println(" -> Livro Cadastrado: ID = " + livro.getId() + ", Título =" + livro.getTitle() + ", Autor =" + livro.getAuthor());
            }

        }catch (Exception e){
            System.out.println("Deu erro: " + e.getMessage());
        }

        try{
            System.out.println("FileInputStream");
            FileInputStream fis = new FileInputStream("livros.txt");
            BookInputStream bisArquivo = new BookInputStream(fis);

            List<Book> LivrosParaCadastro = bisArquivo.CadastrarLivros(); // apos criar uma lista do tipo book, cadastra todos os livros que foram pegues do arquivo livros.txt
            System.out.println("Livros Cadastrados com Sucesso! Total: " + LivrosParaCadastro.size());
            for(Book livro : LivrosParaCadastro){
                System.out.println(" -> " + livro.getTitle() + " (" + livro.getAuthor() + ")");
            }
            fis.close();
        }catch (Exception e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
