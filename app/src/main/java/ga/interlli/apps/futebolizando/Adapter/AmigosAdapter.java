package ga.interlli.apps.futebolizando.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.AmigosCadActivity;
import ga.interlli.apps.futebolizando.Model.Amigo;
import ga.interlli.apps.futebolizando.R;

public class AmigosAdapter extends BaseAdapter {

    // Telas
    final int TELA_AMIGOS_CAD = 11;

    private LayoutInflater layout;
    private final List<Amigo> amigos;
    private ArrayList<Amigo> arrayList;
    private Context context;

    public AmigosAdapter (Context context, List<Amigo> amigos){
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        TextView tvListViewAmigosNome;
        ImageButton ibtnListViewAmigosEdit, ibtnListViewAmigosDelete;
        View view = layout.inflate(R.layout.listview_amigos_item, parent, false);

        tvListViewAmigosNome = view.findViewById(R.id.tvListViewAmigosNome);
        ibtnListViewAmigosEdit = view.findViewById(R.id.ibtnListViewAmigosEdit);
        ibtnListViewAmigosDelete = view.findViewById(R.id.ibtnListViewAmigosDelete);

        Amigo amigo = (Amigo) getItem(position);
        final long amigoId = amigo.getId();

        tvListViewAmigosNome.setText(amigo.getNome());

        ibtnListViewAmigosEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context.getApplicationContext(), AmigosCadActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                it.putExtra("OPERACAO", "EDIT");
                it.putExtra("amigoId", amigoId);
                context.startActivity(it);
            }
        });

        ibtnListViewAmigosDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amigo amigoDelete = Amigo.findById(Amigo.class, amigoId);
                amigoDelete.delete();
                // TODO: *IMPORTANTE* Atualizar a listView após remover do banco!!
            }
        });

        return view;
    }
}
