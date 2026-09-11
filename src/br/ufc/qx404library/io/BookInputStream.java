package br.ufc.qx404library.io;
import br.ufc.qx404library.model.Book;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookInputStream extends InputStream{
    private final InputStream origem;

    public BookInputStream(InputStream origem){
        this.origem = origem;

    }

    @Override
    public int read() throws IOException { // metodo que o InputStream obriga a implementar, ele serve para ler os bytes.
        return this.origem.read();
    }

    public List<Book> CadastrarLivros() throws IOException{
        List<Book> livros = new ArrayList<>();
        StringBuilder linha = new StringBuilder();
        int b;

        while ((b = origem.read()) != -1) {
            char caractere = (char) b;

            if(caractere == '\n'){
                String[] dados = linha.toString().split("[:-]");

                if (dados.length == 3) {
                    Book livro = new Book(dados[0], dados[1], dados[2]);
                    livros.add(livro);
                }
                linha.setLength(0);
            } else {
                linha.append(caractere);
            }
        }
        return livros;
    }
}
