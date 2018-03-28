package ga.interlli.apps.futebolizando.Model;

import java.io.Serializable;

public class Amigo implements Serializable {

    private int id;
    private String nome;
    //private Times time;

    public Amigo() {
    }

    public Amigo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }*/
}
