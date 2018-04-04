package ga.interlli.apps.futebolizando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ga.interlli.apps.futebolizando.Model.Amigo;

public class AmigosCadActivity extends AppCompatActivity {

    Button btnSalvarAmigo;
    EditText edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos_cad);
        binding();

        btnSalvarAmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amigo amigo = new Amigo();
                amigo.setNome(edtNome.getText().toString());
                amigo.save();
                finish();
            }
        });

    }

    private void binding() {
        btnSalvarAmigo = (Button)findViewById(R.id.btnSalvarAmigo);
        edtNome = (EditText)findViewById(R.id.edtNome);
    }
}
