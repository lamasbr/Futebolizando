package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Jogador extends SugarRecord implements Serializable {

    Time time;
    String nomeJogador;

    public Jogador() {
    }

    public Jogador(Time time, String nomeJogador) {
        this.time = time;
        this.nomeJogador = nomeJogador;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }
}
