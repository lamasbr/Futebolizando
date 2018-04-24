package ga.interlli.apps.futebolizando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import ga.interlli.apps.futebolizando.Model.Amigo;
import ga.interlli.apps.futebolizando.Model.Time;

public class TimesCadActivity extends AppCompatActivity {

    // Telas
    final int TELA_JOGADORES = 3;

    List<Amigo> lstAmigos;
    Time time; Amigo amigo;

    Button btnCadTimeSalvar, btnCadTimeDelete, btnTimesCadJogadores;
    EditText edtCadTimeNome;
    Spinner spnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_cad);
        binding();
        preencheSpinner();

        String operacao = getIntent().getExtras().get("OPERACAO").toString();

        switch(operacao){
            case "ADD":
                time = new Time();
                btnCadTimeDelete.setVisibility(View.INVISIBLE);
                break;
            case "EDIT":
                long timeId = (Long) getIntent().getExtras().get("timeId");
                time = Time.findById(Time.class, timeId);
                edtCadTimeNome.setText(time.getNomeTime());
                btnTimesCadJogadores.setVisibility(View.VISIBLE);
                funcaoClickBtnJogadores();
                break;
        }

        btnCadTimeSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amigo = Amigo.findById(Amigo.class, lstAmigos.get(spnTime.getSelectedItemPosition()).getId());
                time.setAmigo(amigo);
                time.setNomeTime(edtCadTimeNome.getText().toString());
                time.save();
                finish();
            }
        });

        btnCadTimeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.delete();
                finish();
            }
        });
    }

    private void binding(){
        btnCadTimeSalvar = (Button) findViewById(R.id.btnCadTimeSalvar);
        btnCadTimeDelete = (Button) findViewById(R.id.btnCadTimeDelete);
        btnTimesCadJogadores = (Button) findViewById(R.id.btnTimesCadJogadores);
        edtCadTimeNome = (EditText) findViewById(R.id.edtCadTimeNome);
        spnTime = (Spinner) findViewById(R.id.spnTime);
    }

    private void preencheSpinner(){
        int idx = 0;
        lstAmigos = Amigo.listAll(Amigo.class);
        String[] amigos = new String[lstAmigos.size()];
        for(Amigo a: lstAmigos){
            amigos[idx] = a.getNome();
            idx++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.spn_times_cad_amigos_item,
                amigos);
        spnTime.setAdapter(adapter);
    }

    private void funcaoClickBtnJogadores(){
        btnTimesCadJogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), JogadoresActivity.class);
                it.putExtra("timeId", time.getId());
                startActivityForResult(it, TELA_JOGADORES);
            }
        });
    }
}
