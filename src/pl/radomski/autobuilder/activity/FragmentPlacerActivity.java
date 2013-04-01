package pl.radomski.autobuilder.activity;

import java.util.ArrayList;

import pl.radomski.autobuilder.fragment.content.grid.GridViewFragment;
import pl.radomski.autobuilder.fragment.footer.standard.FooterFragment;
import pl.radomski.autobuilder.fragment.header.standard.HeaderFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

public class FragmentPlacerActivity extends FrameActivity implements LoaderCallbacks<ArrayList<Fragment>> {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// int viewToLoad =
		// getIntent().getExtras().getInt(ViewDescriptionData.JSON_VIEW_ID);

		getKrwiodawcaLayout().placeHeader(HeaderFragment.create(null));
		getKrwiodawcaLayout().placeContent(GridViewFragment.create(null));
		getKrwiodawcaLayout().placeFooter(FooterFragment.create(null));

		getSupportLoaderManager().initLoader(222, null, this);
	}

	@Override
	public Loader<ArrayList<Fragment>> onCreateLoader(int arg0, Bundle arg1) {
		return null;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<Fragment>> arg0, ArrayList<Fragment> arg1) {

	}

	@Override
	public void onLoaderReset(Loader<ArrayList<Fragment>> arg0) {

	}
}
