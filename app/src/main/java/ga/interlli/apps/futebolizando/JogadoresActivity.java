package ga.interlli.apps.futebolizando;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import ga.interlli.apps.futebolizando.Adapter.JogadoresAdapter;
import ga.interlli.apps.futebolizando.Model.Jogador;
import ga.interlli.apps.futebolizando.Model.Time;

public class JogadoresActivity extends AppCompatActivity {

    // Telas
    final int TELA_JOGADORES_CAD = 31;

    final Context context = this;
    List<Jogador> jogadores;
    Button btnJogadoresCadJogador;
    ListView lvJogadores;
    Time time; Jogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);
        binding();

        final JogadoresAdapter jogadoresAdapter = new JogadoresAdapter(getApplicationContext(), jogadores);
        lvJogadores.setAdapter(jogadoresAdapter);

        btnJogadoresCadJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogador = new Jogador();
                jogador.setTime(time);

                LayoutInflater li = LayoutInflater.from(context);
                View prompsView = li.inflate(R.layout.alertdialog_cadjogador, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(prompsView);

                final EditText edtCadJogadorNome = (EditText) prompsView.findViewById(R.id.edtCadJogadorNome);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        jogador.setNomeJogador(edtCadJogadorNome.getText().toString());
                                        jogador.save();
                                        atualizaListView(); jogadoresAdapter.atualizaListView(jogadores);
                                    }
                                })
                        .setNegativeButton("CANCELAR",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void binding(){
        btnJogadoresCadJogador = (Button) findViewById(R.id.btnJogadoresCadJogador);
        lvJogadores = (ListView) findViewById(R.id.lvJogadores);
        time = Time.findById(Time.class, (Long) getIntent().getExtras().get("timeId"));
        jogadores = Jogador.find(Jogador.class, "time = ?", String.valueOf(time.getId()));
    }

    private void atualizaListView(){
        jogadores.clear();
        jogadores = Jogador.find(Jogador.class, "time = ?", String.valueOf(time.getId()));
    }

}
