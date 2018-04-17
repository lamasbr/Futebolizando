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
    Amigo amigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos_cad);
        binding();

        String operacao = getIntent().getExtras().get("OPERACAO").toString();

        switch (operacao){
            case "ADD":
                amigo = new Amigo();
                break;
            case "EDIT":
                long amigoId = (Long) getIntent().getExtras().get("amigoId");
                amigo = Amigo.findById(Amigo.class, amigoId);
                edtNome.setText(amigo.getNome());
                break;
        }

        btnSalvarAmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
