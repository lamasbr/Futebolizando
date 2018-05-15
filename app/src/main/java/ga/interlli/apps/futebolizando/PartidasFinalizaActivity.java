package ga.interlli.apps.futebolizando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Adapter.GolsAdapter;
import ga.interlli.apps.futebolizando.Model.Amigo;
import ga.interlli.apps.futebolizando.Model.Jogador;
import ga.interlli.apps.futebolizando.Model.Partida;
import ga.interlli.apps.futebolizando.Model.Time;

public class PartidasFinalizaActivity extends AppCompatActivity {

    ListView lvFinalizaPartidaGolsTimeA, lvFinalizaPartidaGolsTimeB;
    TextView tvFinalizaPartidaTimeA, tvFinalizaPartidaTimeB;
    Button btnFinalizaPartidaSalvaPlacar;

    List<Jogador> jogadoresTimeA, jogadoresTimeB;
    Time timeA, timeB;
    GolsAdapter golsAdapterTimeA, golsAdapterTimeB;
    Partida partida;
    Jogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidas_finaliza);
        binding();

        tvFinalizaPartidaTimeA.setText("Gols do time " + timeA.getNomeTime().toString());
        tvFinalizaPartidaTimeB.setText("Gols do time " + timeB.getNomeTime().toString());

        golsAdapterTimeA = new GolsAdapter(getApplicationContext(), jogadoresTimeA);
        golsAdapterTimeB = new GolsAdapter(getApplicationContext(), jogadoresTimeB);

        lvFinalizaPartidaGolsTimeA.setAdapter(golsAdapterTimeA);
        lvFinalizaPartidaGolsTimeB.setAdapter(golsAdapterTimeB);

        btnFinalizaPartidaSalvaPlacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Jogador> jogadoresSelecionadosTimeA = golsAdapterTimeA.getJogadoresSelecionados();
                List<Jogador> jogadoresSelecionadosTimeB = golsAdapterTimeB.getJogadoresSelecionados();
                int placarTimeA = 0; int placarTimeB = 0;

                if(!partida.isFinalizada()){
                    for (Jogador jS: jogadoresSelecionadosTimeA) {
                        jogador = Jogador.findById(Jogador.class, jS.getId());
                        jogador.setGolsMarcados(jogador.getGolsMarcados() + jS.getGolsMarcados());
                        jogador.save();
                        placarTimeA += jS.getGolsMarcados();
                    }

                    for (Jogador jS: jogadoresSelecionadosTimeB) {
                        jogador = Jogador.findById(Jogador.class, jS.getId());
                        jogador.setGolsMarcados(jogador.getGolsMarcados() + jS.getGolsMarcados());
                        jogador.save();
                        placarTimeB += jS.getGolsMarcados();
                    }

                    partida.finalizarPartida(placarTimeA, placarTimeB);
                    partida.save();

                    if(placarTimeA == placarTimeB){
                        Amigo amigoA = Amigo.findById(Amigo.class, timeA.getAmigo().getId());
                        amigoA.atualizaPontuacao(false, placarTimeA, placarTimeB);
                        Amigo amigoB = Amigo.findById(Amigo.class, timeB.getAmigo().getId());
                        amigoB.atualizaPontuacao(false, placarTimeB, placarTimeA);
                        amigoA.save(); amigoB.save();
                    }
                    if(placarTimeA > placarTimeB){
                        Amigo amigoA = Amigo.findById(Amigo.class, timeA.getAmigo().getId());
                        amigoA.atualizaPontuacao(true, placarTimeA, placarTimeB);
                        Amigo amigoB = Amigo.findById(Amigo.class, timeB.getAmigo().getId());
                        amigoB.atualizaPontuacao(false, placarTimeB, placarTimeA);
                        amigoA.save(); amigoB.save();
                    }
                    if(placarTimeB > placarTimeA){
                        Amigo amigoA = Amigo.findById(Amigo.class, timeA.getAmigo().getId());
                        amigoA.atualizaPontuacao(false, placarTimeA, placarTimeB);
                        Amigo amigoB = Amigo.findById(Amigo.class, timeB.getAmigo().getId());
                        amigoB.atualizaPontuacao(true, placarTimeB, placarTimeA);
                        amigoA.save(); amigoB.save();
                    }
                }
                finish();
            }
        });

    }

    private void binding(){
        lvFinalizaPartidaGolsTimeA = (ListView) findViewById(R.id.lvFinalizaPartidaGolsTimeA);
        lvFinalizaPartidaGolsTimeB = (ListView) findViewById(R.id.lvFinalizaPartidaGolsTimeB);
        btnFinalizaPartidaSalvaPlacar = (Button) findViewById(R.id.btnFinalizaPartidaSalvaPlacar);
        tvFinalizaPartidaTimeA = (TextView) findViewById(R.id.tvFinalizaPartidaTimeA);
        tvFinalizaPartidaTimeB = (TextView) findViewById(R.id.tvFinalizaPartidaTimeB);

        partida = Partida.findById(Partida.class, (Long) getIntent().getExtras().get("partidaId"));
        timeA = partida.getTimeA(); timeB = partida.getTimeB();
        jogadoresTimeA = Jogador.find(Jogador.class, "time = ?", String.valueOf(timeA.getId()));
        jogadoresTimeB = Jogador.find(Jogador.class, "time = ?", String.valueOf(timeB.getId()));
    }
}
