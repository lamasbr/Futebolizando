package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;
import java.io.Serializable;

public class Amigo extends SugarRecord implements Serializable {

    String nome;

    public Amigo() {
    }

    public Amigo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
