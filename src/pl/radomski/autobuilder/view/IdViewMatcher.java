package pl.radomski.autobuilder.view;

import pl.radomski.autobuilder.fragment.content.grid.GridData;
import pl.radomski.autobuilder.fragment.content.grid.GridViewFragment;
import pl.radomski.autobuilder.fragment.footer.standard.FooterData;
import pl.radomski.autobuilder.fragment.footer.standard.FooterFragment;
import pl.radomski.autobuilder.fragment.header.standard.HeaderData;
import pl.radomski.autobuilder.fragment.header.standard.HeaderFragment;
import pl.radomski.autobuilder.view.data.ViewData;
import android.support.v4.app.Fragment;

public class IdViewMatcher {
	public static Fragment matchId(int id) {

		return null;
	}

	public static ViewData matchHeader(int viewId) {
		ViewData data;
		switch (viewId) {
		case HeaderFragment.TYPE_ID:
			data = new HeaderData();
			break;
		default:
			throw new IllegalArgumentException(" No view defined for this id:" + viewId);
		}
		return data;
	}

	public static ViewData matchContent(int viewId) {
		ViewData data;
		switch (viewId) {
		case GridViewFragment.TYPE_ID:
			data = new GridData();
			break;
		default:
			throw new IllegalArgumentException(" No view defined for this id:" + viewId);
		}
		return data;
	}

	public static ViewData matchFooter(int viewId) {
		ViewData data;
		switch (viewId) {
		case FooterFragment.TYPE_ID:
			data = new FooterData();
			break;
		default:
			throw new IllegalArgumentException(" No view defined for this id:" + viewId);
		}
		return data;
	}
}
