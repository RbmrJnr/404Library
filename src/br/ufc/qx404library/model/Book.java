package br.ufc.qx404library.model;
import java.io.Serializable;

public class Book implements Serializable{
    private String id;
    private String title;
    private String author;

    public Book(){
        id = "Sem ID";
        title = "Sem Título";
        author = "Sem Autor";
    }

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }
}


