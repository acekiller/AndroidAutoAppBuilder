package pl.radomski.autobuilder.fragment.content.grid;

import java.util.Collection;

import pl.radomski.autobuilder.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GridAdapter<T> extends ArrayAdapter<T> {

	private int layoutId;
	private LayoutInflater inflater;

	public GridAdapter(Context context, int layoutId) {
		super(context, 0);
		this.layoutId = layoutId;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.pos);
		textView.setText(position + "");

		return convertView;
	}

	@Override
	public void addAll(Collection<? extends T> collection) {
		for (T t : collection) {
			add(t);
		}
	}

}
