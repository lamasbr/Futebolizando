package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;
import java.io.Serializable;

public class Amigo extends SugarRecord implements Serializable {

    String nome;
    double pontuacao;

    public Amigo() {
    }

    public Amigo(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void atualizaPontuacao(boolean vitoria, int golFeito, int golTomado){
        if(!vitoria){
            this.pontuacao += golFeito + (golTomado*0.5);
        } else {
            this.pontuacao += 3 + golFeito + (golTomado*0.5);
        }
    }

    @Override
    public String toString() {
        return getNome();
    }
}
