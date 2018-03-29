package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;
import java.io.Serializable;

public class Time extends SugarRecord implements Serializable {

    Amigo amigo;
    String nomeTime;

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public Time(Amigo amigo, String nomeTime) {

        this.amigo = amigo;
        this.nomeTime = nomeTime;
    }

    public Time() {

    }
}
