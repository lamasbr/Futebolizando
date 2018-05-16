package ga.interlli.apps.futebolizando;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ga.interlli.apps.futebolizando.Adapter.RankingAdapter;
import ga.interlli.apps.futebolizando.Model.Amigo;

public class RankingActivity extends AppCompatActivity {

    ListView lvRanking;
    List<Amigo> amigos;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        binding();

        final RankingAdapter rankingAdapter = new RankingAdapter(getApplicationContext(), amigos);
        lvRanking.setAdapter(rankingAdapter);


    }

    private void binding(){
        lvRanking = (ListView) findViewById(R.id.lvRanking);
        amigos = Amigo.findWithQuery(Amigo.class, "select * from amigo order by pontuacao DESC");
    }
}
