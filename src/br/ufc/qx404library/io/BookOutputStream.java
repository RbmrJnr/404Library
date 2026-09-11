package br.ufc.qx404library.io;
import br.ufc.qx404library.model.Book;

import java.io.IOException;
import java.io.OutputStream;

public class BookOutputStream extends OutputStream{
    private OutputStream destino;
    private Book[] livros;
    private int qnt;

    public BookOutputStream(OutputStream destino, Book[] livros, int qnt){
        this.destino = destino;
        this.livros = livros;
        this.qnt = qnt;

    }

    @Override
    public void write(int b) throws IOException {
        destino.write(b);
    } // metodo que o OutputStream obriga a implementar, ele serve para escrever os bytes.

    public void enviarLivros() throws IOException{
        for(int i = 0; i < qnt; i++){
            Book livro = livros[i];

            if(livro != null){
                String linhaInfo = livro.getId() + ": " + livro.getTitle() + " - " + livro.getAuthor() + "\n";

                destino.write(linhaInfo.getBytes());
            }
        }
        destino.flush(); // Força a escrita imediata dos dados
    }
}
