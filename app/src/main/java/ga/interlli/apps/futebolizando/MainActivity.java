package ga.interlli.apps.futebolizando;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Amigo;
import ga.interlli.apps.futebolizando.Model.Jogador;
import ga.interlli.apps.futebolizando.Model.Partida;
import ga.interlli.apps.futebolizando.Model.Time;

public class MainActivity extends AppCompatActivity {

    Button btnAmigos, btnTimes, btnPartidas, btnRelPlacarJogos, btnRelRanking, btnMainZerarTudo;
    AlertDialog alerta;

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

        btnMainZerarTudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertaZerarTudo(v);
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
        btnMainZerarTudo = (Button)findViewById(R.id.btnMainZerarTudo);
    }

    private void zerarTodosDados(){
        Partida.deleteAll(Partida.class);
        Jogador.deleteAll(Jogador.class);
        Time.deleteAll(Time.class);
        Amigo.deleteAll(Amigo.class);
    }

    private void alertaZerarTudo(final View view){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Limpar dados?");
        alertBuilder.setMessage("Deseja realmente limpar todos os dados do aplicativo?");

        alertBuilder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zerarTodosDados();
                mostraSnackbar(view, "TODOS OS DADOS FORAM REMOVIDOS", Snackbar.LENGTH_LONG);
            }
        });
        alertBuilder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alerta = alertBuilder.create();
        alerta.show();
    }

    private void mostraSnackbar(View view, String mensagem, int duracao) {
        final Snackbar snackbar = Snackbar.make(view, mensagem, duracao);
        snackbar.show();
    }
}
