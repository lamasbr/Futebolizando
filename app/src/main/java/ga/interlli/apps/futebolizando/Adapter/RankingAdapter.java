package ga.interlli.apps.futebolizando.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Amigo;
import ga.interlli.apps.futebolizando.R;

public class RankingAdapter extends BaseAdapter {

    private LayoutInflater layout;
    private final List<Amigo> amigos;
    private ArrayList<Amigo> arrayList;

    public RankingAdapter(Context context, List<Amigo> amigos){
        this.amigos = amigos;
        layout = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Amigo>();
        this.arrayList.addAll(amigos);
    }

    @Override
    public int getCount() {
        return amigos.size();
    }

    @Override
    public Object getItem(int position) {
        return amigos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layout.inflate(R.layout.listview_ranking_item, parent, false);
        TextView tvRankingPosicao = view.findViewById(R.id.tvRankingPosicao);
        TextView tvRankingNomeAmigo = view.findViewById(R.id.tvRankingNomeAmigo);
        TextView tvRankingPontos = view.findViewById(R.id.tvRankingPontos);
        Amigo amigo = (Amigo) getItem(position);

        tvRankingPosicao.setText(String.valueOf(position + 1));
        tvRankingNomeAmigo.setText(amigo.getNome());
        tvRankingPontos.setText(String.valueOf(amigo.getPontuacao()));

        return view;
    }
}
