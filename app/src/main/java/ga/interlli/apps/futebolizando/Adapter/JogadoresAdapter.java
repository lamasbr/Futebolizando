package ga.interlli.apps.futebolizando.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Jogador;
import ga.interlli.apps.futebolizando.R;

public class JogadoresAdapter extends BaseAdapter {

    private LayoutInflater layout;
    private List<Jogador> jogadores;
    private ArrayList<Jogador> arrayList;
    private Context context;

    public JogadoresAdapter (Context context, List<Jogador> jogadores){
        this.context = context;
        this.jogadores = jogadores;
        layout = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Jogador>();
        this.arrayList.addAll(jogadores);
    }

    @Override
    public int getCount() {
        return jogadores.size();
    }

    @Override
    public Object getItem(int position) {
        return jogadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvListViewJogadoresNome;
        View view = layout.inflate(R.layout.listview_jogadores_item, parent, false);
        tvListViewJogadoresNome = view.findViewById(R.id.tvListViewJogadoresNome);

        Jogador jogador = (Jogador) getItem(position);
        final long jogadorId = jogador.getId();

        tvListViewJogadoresNome.setText(jogador.getNomeJogador());

        return view;
    }

    public void atualizaListView(List<Jogador> jogadores){
        this.jogadores = jogadores;
        notifyDataSetChanged();
    }
}
