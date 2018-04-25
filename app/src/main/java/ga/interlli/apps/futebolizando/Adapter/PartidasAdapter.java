package ga.interlli.apps.futebolizando.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Partida;
import ga.interlli.apps.futebolizando.R;

public class PartidasAdapter extends BaseAdapter {

    private LayoutInflater layout;
    private List<Partida> partidas;
    private ArrayList<Partida> arrayList;

    public PartidasAdapter(Context context, List<Partida> partidas){
        this.partidas = partidas;
        layout = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Partida>();
        this.arrayList.addAll(partidas);
    }

    @Override
    public int getCount() {
        return partidas.size();
    }

    @Override
    public Object getItem(int position) {
        return partidas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layout.inflate(R.layout.listview_partidas_item, parent, false);
        TextView tvPartidaNomeTimeA = view.findViewById(R.id.tvPartidaNomeTimeA);
        TextView tvPartidaNomeTimeB = view.findViewById(R.id.tvPartidaNomeTimeB);
        TextView tvPartidaGolsTimeA = view.findViewById(R.id.tvPartidaGolsTimeA);
        TextView tvPartidaGolsTimeB = view.findViewById(R.id.tvPartidaGolsTimeB);

        Partida partida = (Partida) getItem(position);
        tvPartidaNomeTimeA.setText(partida.getTimeA().getNomeTime());
        tvPartidaGolsTimeA.setText(String.valueOf(partida.getGolsTimeA()));
        tvPartidaNomeTimeB.setText(partida.getTimeB().getNomeTime());
        tvPartidaGolsTimeB.setText(String.valueOf(partida.getGolsTimeB()));

        return view;
    }

    public void atualizaListView(List<Partida> partidas){
        this.partidas = partidas;
        notifyDataSetChanged();
    }
}
