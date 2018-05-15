package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.List;

public class Jogador extends SugarRecord implements Serializable {

    Time time;
    String nomeJogador;
    int golsMarcados;

    @Ignore
    boolean isSelected;

    public Jogador() {
    }

    public Jogador(Time time, String nomeJogador, int golsMarcados) {
        this.time = time;
        this.nomeJogador = nomeJogador;
        this.golsMarcados = golsMarcados;
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

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public void setGolsMarcados(int golsMarcados) {
        this.golsMarcados = golsMarcados;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static Jogador getArtilheiro(){
        Jogador artilheiro = new Jogador(); artilheiro.setGolsMarcados(0);
        List<Jogador> jogadores = Jogador.findWithQuery(Jogador.class, "SELECT * FROM Jogador ORDER BY ? DESC", "golsMarcados");

        for(Jogador jogador: jogadores){
            if (jogador.getGolsMarcados() > artilheiro.getGolsMarcados()){
                artilheiro = jogador;
            }
        }
        return artilheiro;
    }
}
