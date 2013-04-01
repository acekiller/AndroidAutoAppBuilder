package pl.radomski.autobuilder.fragment.content.grid;

import pl.radomski.autobuilder.R;
import pl.radomski.autobuilder.view.data.ViewData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class GridViewFragment extends Fragment {

	public static final int TYPE_ID = ViewData.MAIN_CONTENT_BOUND_MIN + 0;

	public static GridViewFragment create(Bundle bundle) {
		GridViewFragment fragment = new GridViewFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	private GridView gridView;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_grid, container, false);
		gridView = (GridView) view.findViewById(R.id.gridFragment_grid);

		GridAdapter<String> gridAdapter = new GridAdapter<String>(getActivity(), R.layout.element_grid);
		for (int i = 0; i < 10; i++) {
			gridAdapter.add("aaa");
		}
		gridView.setAdapter(gridAdapter);
		return view;
	}
}