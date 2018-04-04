package ga.interlli.apps.futebolizando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ga.interlli.apps.futebolizando.Model.Amigo;

public class AmigosActivity extends AppCompatActivity {

    // Telas
    final int TELA_AMIGOS_CAD = 11;

    List<Amigo> amigos;
    Button btnCadAmigos;
    ListView lvAmigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);
        binding();

        btnCadAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AmigosCadActivity.class);
                startActivityForResult(it, TELA_AMIGOS_CAD);
            }
        });

        long count = Amigo.count(Amigo.class);
        if(count > 0) {
            amigos = Amigo.listAll(Amigo.class);
            ArrayAdapter<Amigo> adapter = new ArrayAdapter<Amigo>(this, android.R.layout.simple_list_item_1, amigos);
            lvAmigos.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        long count = Amigo.count(Amigo.class);
        if(count > 0) {
            amigos = Amigo.listAll(Amigo.class);
            ArrayAdapter<Amigo> adapter = new ArrayAdapter<Amigo>(this, android.R.layout.simple_list_item_1, amigos);
            lvAmigos.setAdapter(adapter);
        }
    }

    private void binding() {
        btnCadAmigos = (Button)findViewById(R.id.btnCadAmigos);
        lvAmigos = (ListView)findViewById(R.id.lvAmigos);
    }
}
