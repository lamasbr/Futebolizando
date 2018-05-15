package ga.interlli.apps.futebolizando.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Jogador;
import ga.interlli.apps.futebolizando.R;

public class GolsAdapter extends BaseAdapter {

    private Context context;
    private List<Jogador> jogadores;
    private ArrayList<Jogador> arrayList;
    private LayoutInflater layout;
    private final List<Long> idSelecionados = new ArrayList<Long>();
    private final List<Jogador> jogadoresSelecionados = new ArrayList<Jogador>();

    public GolsAdapter (Context context, List<Jogador> jogadores){
        this.context = context;
        this.jogadores = jogadores;
        layout = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Jogador>();
        this.arrayList.addAll(jogadores);
    }

    public void adicionaJogador(Jogador jogador){
        this.jogadores.add(jogador);
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
        View view = convertView;

        if(view == null){
            layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layout.inflate(R.layout.listview_partidas_gols_item, null);
        }

        CheckBox cbJogadorGol = (CheckBox) view.findViewById(R.id.cbJogadorGol);
        final EditText edtJogadorNumGols = (EditText) view.findViewById(R.id.edtJogadorNumGols);

        Jogador jogador = jogadores.get(position);

        cbJogadorGol.setText(jogador.getNomeJogador());
        cbJogadorGol.setChecked(jogador.isSelected());
        cbJogadorGol.setTag(jogador);

        cbJogadorGol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check = (CheckBox) v;
                final Jogador j = (Jogador) v.getTag();
                j.setSelected(((CheckBox) v).isChecked());

                if(check.isChecked()) {
                    edtJogadorNumGols.setVisibility(View.VISIBLE);

                    edtJogadorNumGols.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            int gols = j.getGolsMarcados() + Integer.parseInt(edtJogadorNumGols.getText().toString());
                            j.setGolsMarcados(gols);
                        }
                    });

                    if(!jogadoresSelecionados.contains(j)){
                        jogadoresSelecionados.add(j);
                    }
                } else {
                    edtJogadorNumGols.setVisibility(View.INVISIBLE);
                    if(jogadoresSelecionados.contains(j)){
                        jogadoresSelecionados.remove(j);
                    }
                }
            }
        });

        return view;
    }

    //public List<Long> getIdSelecionados(){
    //    return this.idSelecionados;
    //}

    public List<Jogador> getJogadoresSelecionados(){
        return this.jogadoresSelecionados;
    }
}
