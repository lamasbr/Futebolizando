package ga.interlli.apps.futebolizando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Amigo;

public class MainActivity extends AppCompatActivity {

    Button btnAmigos, btnTimes, btnPartidas, btnRelPlacarJogos, btnRelRanking;

    // Telas
    final int TELA_AMIGOS = 1;
    final int TELA_TIMES = 2;
    final int TELA_JOGADORES = 3;
    final int TELA_PARTIDAS = 4;
    final int TELA_REL_PLACARJOGOS = 5;
    final int TELA_REL_RANKING = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding();

        // Abre tela para visualizar e cadastrar amigos
        btnAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AmigosActivity.class);
                startActivityForResult(it, TELA_AMIGOS);
            }
        });

        // TODO: Abre tela para visualizar e cadastrar times dos amigos
        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TimesActivity.class);
                startActivityForResult(it, TELA_TIMES);
            }
        });

        btnPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), PartidasActivity.class);
                startActivityForResult(it, TELA_PARTIDAS);
            }
        });

        // TODO: Abre tela para visualizar e cadastrar jogadores dos times (escalação)

        // TODO:

    }

    private void binding() {
        btnAmigos = (Button)findViewById(R.id.btnAmigos);
        btnTimes = (Button)findViewById(R.id.btnTimes);
        btnPartidas = (Button)findViewById(R.id.btnPartidas);
        btnRelPlacarJogos = (Button)findViewById(R.id.btnRelPlacarJogos);
        btnRelRanking = (Button)findViewById(R.id.btnRelRanking);
    }
}
