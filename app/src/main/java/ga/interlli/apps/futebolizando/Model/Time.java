package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.List;

public class Time extends SugarRecord implements Serializable {

    Amigo amigo;
    String nomeTime;

    @Ignore
    List<Jogador> jogadores;

    public Time(Amigo amigo, String nomeTime, List<Jogador> jogadores) {
        this.amigo = amigo;
        this.nomeTime = nomeTime;
        this.jogadores = jogadores;
    }

    public Time(Amigo amigo, String nomeTime) {

        this.amigo = amigo;
        this.nomeTime = nomeTime;
    }

    public Time() {

    }

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

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
