package ga.interlli.apps.futebolizando.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Time;
import ga.interlli.apps.futebolizando.R;

public class TimesAdapter extends BaseAdapter {

    private LayoutInflater layout;
    private final List<Time> times;
    private ArrayList<Time> arrayList;

    public TimesAdapter(Context context, List<Time> times){
        this.times = times;
        layout = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Time>();
        this.arrayList.addAll(times);
    }

    @Override
    public int getCount() {
        return times.size();
    }

    @Override
    public Object getItem(int position) {
        return times.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layout.inflate(R.layout.listview_times_item, parent, false);
        TextView tvListViewTimesNome = view.findViewById(R.id.tvListViewTimesNome);
        Time time = (Time) getItem(position);

        tvListViewTimesNome.setText(time.getNomeTime());

        return view;
    }
}
