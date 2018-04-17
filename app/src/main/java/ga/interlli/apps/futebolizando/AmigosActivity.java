package ga.interlli.apps.futebolizando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ga.interlli.apps.futebolizando.Adapter.AmigosAdapter;
import ga.interlli.apps.futebolizando.Model.Amigo;

public class AmigosActivity extends AppCompatActivity {

    // Telas
    final int TELA_AMIGOS_CAD = 11;
    final int TELA_TIMES_CAD = 21;

    List<Amigo> amigos;
    Button btnCadAmigos;
    ListView lvAmigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);
        binding();

        amigos = Amigo.listAll(Amigo.class);
        final AmigosAdapter amigosAdapter = new AmigosAdapter(getApplicationContext(), amigos);
        lvAmigos.setAdapter(amigosAdapter);

        btnCadAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AmigosCadActivity.class);
                it.putExtra("OPERACAO", "ADD");
                startActivityForResult(it, TELA_AMIGOS_CAD);
            }
        });

        lvAmigos.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), TimesCadActivity.class);
                Amigo amigo = amigos.get(position);
                it.putExtra("amigo", amigo);
                startActivityForResult(it, TELA_TIMES_CAD);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        amigos = Amigo.listAll(Amigo.class);
        final AmigosAdapter amigosAdapter = new AmigosAdapter(getApplicationContext(), amigos);
        lvAmigos.setAdapter(amigosAdapter);
    }

    private void binding() {
        btnCadAmigos = (Button)findViewById(R.id.btnCadAmigos);
        lvAmigos = (ListView)findViewById(R.id.lvAmigos);
    }
}
