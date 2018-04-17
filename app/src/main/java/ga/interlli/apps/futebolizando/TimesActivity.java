package ga.interlli.apps.futebolizando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ga.interlli.apps.futebolizando.Adapter.TimesAdapter;
import ga.interlli.apps.futebolizando.Model.Time;

public class TimesActivity extends AppCompatActivity {

    // Telas
    final int TELA_TIMES_CAD = 21;

    List<Time> times;
    Button btnCadTimes;
    ListView lvTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
        binding();

        times = Time.listAll(Time.class);
        final TimesAdapter timesAdapter = new TimesAdapter(getApplicationContext(), times);
        lvTimes.setAdapter(timesAdapter);

        btnCadTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TimesCadActivity.class);
                it.putExtra("OPERACAO", "ADD");
                startActivityForResult(it, TELA_TIMES_CAD);
            }
        });

        lvTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), TimesCadActivity.class);
                Time time = times.get(position);
                it.putExtra("OPERACAO", "EDIT");
                it.putExtra("timeId", time.getId());
                startActivityForResult(it, TELA_TIMES_CAD);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        times = Time.listAll(Time.class);
        final TimesAdapter timesAdapter = new TimesAdapter(getApplicationContext(), times);
        lvTimes.setAdapter(timesAdapter);
    }

    private void binding(){
        btnCadTimes = (Button) findViewById(R.id.btnCadTimes);
        lvTimes = (ListView) findViewById(R.id.lvTimes);
    }
}
