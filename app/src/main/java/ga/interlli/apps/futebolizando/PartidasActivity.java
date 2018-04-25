package ga.interlli.apps.futebolizando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ga.interlli.apps.futebolizando.Adapter.PartidasAdapter;
import ga.interlli.apps.futebolizando.Model.Partida;
import ga.interlli.apps.futebolizando.Model.Time;

public class PartidasActivity extends AppCompatActivity {

    List<Partida> partidas;
    Button btnPartidasGerarPartida;
    ListView lvPartidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidas);
        binding();

        partidas = Partida.listAll(Partida.class);
        final PartidasAdapter partidasAdapter = new PartidasAdapter(getApplicationContext(), partidas);
        lvPartidas.setAdapter(partidasAdapter);

        btnPartidasGerarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partidas = sortearPartidas();
                partidasAdapter.atualizaListView(partidas);
            }
        });
    }

    private void binding(){
        btnPartidasGerarPartida = (Button) findViewById(R.id.btnPartidasGerarPartida);
        lvPartidas = (ListView) findViewById(R.id.lvPartidas);
    }

    private List<Partida> sortearPartidas(){
        List<Time> times = Time.listAll(Time.class);
        partidas.clear();

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
}
