package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class GolsJogador extends SugarRecord implements Serializable {

    Partida partida;
    Jogador jogador;
    int qtdGols;

    public GolsJogador() {
    }

    public GolsJogador(Partida partida, Jogador jogador) {
        this.partida = partida;
        this.jogador = jogador;
        this.qtdGols = 0;
    }

    public GolsJogador(Partida partida, Jogador jogador, int qtdGols) {
        this.partida = partida;
        this.jogador = jogador;
        this.qtdGols = qtdGols;
    }

    public Partida getPartida() {
        return partida;
    }

    public Partida getPartida(int idPartida) {
        partida = Partida.findById(Partida.class, idPartida);

        if (partida != null)
            return partida;

        return null;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Jogador getJogador(int idJogador) {
        jogador = Jogador.findById(Jogador.class, idJogador);

        if(jogador != null)
            return jogador;

        return null;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getQtdGols() {
        return qtdGols;
    }

    public void setQtdGols(int qtdGols) {
        this.qtdGols = qtdGols;
    }
}
