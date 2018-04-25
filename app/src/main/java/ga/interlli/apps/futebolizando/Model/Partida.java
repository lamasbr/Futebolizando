package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partida extends SugarRecord implements Serializable {

    Time timeA;
    Time timeB;
    int golsTimeA;
    int golsTimeB;
    boolean finalizada;

    public Partida() {
    }

    public Partida(Time timeA, Time timeB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.golsTimeA = 0;
        this.golsTimeB = 0;
        this.finalizada = false;
    }

    public Time getTimeA() {
        return timeA;
    }

    public void setTimeA(Time timeA) {
        this.timeA = timeA;
    }

    public Time getTimeB() {
        return timeB;
    }

    public void setTimeB(Time timeB) {
        this.timeB = timeB;
    }

    public int getGolsTimeA() {
        return golsTimeA;
    }

    public void setGolsTimeA(int golsTimeA) {
        this.golsTimeA = golsTimeA;
    }

    public int getGolsTimeB() {
        return golsTimeB;
    }

    public void setGolsTimeB(int golsTimeB) {
        this.golsTimeB = golsTimeB;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public static List<Partida> sortearPartidas(){
        List<Time> times = Time.listAll(Time.class);
        List<Partida> partidas = new ArrayList<>();

        while(!times.isEmpty()){
            if((times.size() % 2) == 0){
                Partida partida;
                Time tA, tB;
                Collections.shuffle(times);
                tA = times.get(0); times.remove(0);
                tB = times.get(0); times.remove(0);
                partida = new Partida(tA, tB);
                partida.save();

                partidas.add(partida);
            }
        }
        return partidas;
    }

    public void finalizarPartida(Partida partida, int golsTimeA, int golsTimeB){
        //Partida partida = Partida.findById(Partida.class, idPartida);

        if(!this.isFinalizada()){
            this.setGolsTimeA(golsTimeA);
            this.setGolsTimeB(golsTimeB);
            this.setFinalizada(true);
        }
    }
}
