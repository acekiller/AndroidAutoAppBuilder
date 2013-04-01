package pl.radomski.autobuilder.fragment.header.standard;

import pl.radomski.autobuilder.R;
import pl.radomski.autobuilder.view.data.ViewData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HeaderFragment extends Fragment {
	public static final int TYPE_ID = ViewData.HEADER_CONTENT_BOUND_MIN + 0;

	public static HeaderFragment create(Bundle bundle) {
		HeaderFragment fragment = new HeaderFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_header, container, false);
		return view;
	}

}
