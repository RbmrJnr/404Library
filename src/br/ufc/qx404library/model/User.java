package br.ufc.qx404library.model;
import java.io.Serializable;

public class User implements Serializable{
    private String id;
    private String username;
    private String matricula;

    public User(){
        this.id = "Sem ID";
        this.username = "Sem Username";
        this.matricula = "Sem Matrícula";
    }

    public User(String id, String username, String matricula){
        this.id = id;
        this.username = username;
        this.matricula = matricula;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return this.matricula;
    }
}
