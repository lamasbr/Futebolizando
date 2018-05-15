package ga.interlli.apps.futebolizando;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ga.interlli.apps.futebolizando.Adapter.PartidasAdapter;
import ga.interlli.apps.futebolizando.Model.Jogador;
import ga.interlli.apps.futebolizando.Model.Partida;
import ga.interlli.apps.futebolizando.Model.Time;

public class PartidasActivity extends AppCompatActivity {

    // Telas
    final int TELA_PARTIDAS_FINALIZA = 41;

    List<Partida> partidas;
    Button btnPartidasGerarPartida, btnPartidasLimparPartidas, btnFinalizarRodada;
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
                partidas = sortearPartidas(v);
                partidasAdapter.atualizaListView(partidas);
            }
        });

        btnPartidasLimparPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partidas.clear();
                Partida.deleteAll(Partida.class);
                partidasAdapter.atualizaListView(partidas);
                btnPartidasGerarPartida.setEnabled(true);
                btnPartidasLimparPartidas.setEnabled(false);
                mostraSnackbar(v, "PARTIDAS REMOVIDAS!", Snackbar.LENGTH_LONG);
            }
        });

        btnFinalizarRodada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Partida.finalizarRodada(partidas);
                btnFinalizarRodada.setEnabled(false);

                // TODO: Mostra artilheiro e equipe pertencente

                finish();
            }
        });

        lvPartidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), PartidasFinalizaActivity.class);
                Partida partida = partidas.get(position);
                it.putExtra("partidaId", partida.getId());
                startActivityForResult(it, TELA_PARTIDAS_FINALIZA);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final PartidasAdapter partidasAdapter = new PartidasAdapter(getApplicationContext(), partidas);
        partidas = Partida.listAll(Partida.class);
        partidasAdapter.atualizaListView(partidas);
    }

    private void binding(){
        btnPartidasGerarPartida = (Button) findViewById(R.id.btnPartidasGerarPartida);
        btnPartidasLimparPartidas = (Button) findViewById(R.id.btnPartidasLimparPartidas);
        btnFinalizarRodada = (Button) findViewById(R.id.btnFinalizarRodada);
        lvPartidas = (ListView) findViewById(R.id.lvPartidas);

        if(Partida.listAll(Partida.class).size() != 0){
            btnPartidasGerarPartida.setEnabled(false);
            btnPartidasLimparPartidas.setEnabled(true);
            btnFinalizarRodada.setEnabled(true);
        } else {
            btnPartidasGerarPartida.setEnabled(true);
            btnPartidasLimparPartidas.setEnabled(false);
            btnFinalizarRodada.setEnabled(false);
        }
    }

    private List<Partida> sortearPartidas(View v){
        List<Time> times = Time.listAll(Time.class);
        partidas.clear();

        if(times.isEmpty()){
            mostraSnackbar(v, "NENHUM TIME CADASTRADO!", Snackbar.LENGTH_LONG);
        } else if(((times.size() % 2) != 0)) {
            mostraSnackbar(v, "CADASTRE OU REMOVA MAIS UM TIME!", Snackbar.LENGTH_LONG);
        } else {
            while(!times.isEmpty() && ((times.size() % 2) == 0)){
                Partida partida;
                Time tA, tB;
                Collections.shuffle(times);
                tA = times.get(0); times.remove(0);
                tB = times.get(0); times.remove(0);
                partida = new Partida(tA, tB);
                partida.save();

                partidas.add(partida);
            }
            mostraSnackbar(v, "RODADA SORTEADA!", Snackbar.LENGTH_LONG);
            btnPartidasGerarPartida.setEnabled(false);
            btnPartidasLimparPartidas.setEnabled(true);
        }

        return partidas;
    }

    private void mostraSnackbar(View view, String mensagem, int duracao){
        final Snackbar snackbar = Snackbar.make(view, mensagem, duracao);
        snackbar.show();
    }
}
