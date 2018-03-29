package ga.interlli.apps.futebolizando.Model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Partida extends SugarRecord implements Serializable {

    Time timeA;
    Time timeB;
    int golsTimeA;
    int golsTimeB;

    public Partida() {
    }

    public Partida(Time timeA, Time timeB, int golsTimeA, int golsTimeB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.golsTimeA = golsTimeA;
        this.golsTimeB = golsTimeB;
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
}
