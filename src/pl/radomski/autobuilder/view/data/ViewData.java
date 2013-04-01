package pl.radomski.autobuilder.view.data;

import org.json.JSONException;
import org.json.JSONObject;

import pl.radomski.autobuilder.view.IdViewMatcher;
import android.os.Parcelable;

public abstract class ViewData implements Parcelable {

	public static final int MAIN_CONTENT_BOUND_MIN = 2000;
	public static final int MAIN_CONTENT_BOUND_MAX = 2999;
	public static final int HEADER_CONTENT_BOUND_MIN = 3000;
	public static final int HEADER_CONTENT_BOUND_MAX = 3999;
	public static final int FOOTER_CONTENT_BOUND_MIN = 4000;
	public static final int FOOTER_CONTENT_BOUND_MAX = 4999;

	public static ViewData createFromJson(JSONObject jsonObject, int viewId) throws JSONException {
		if (viewId < MAIN_CONTENT_BOUND_MIN) {
			throw new IllegalArgumentException("ID is lower than:" + MAIN_CONTENT_BOUND_MIN
					+ " No view defined for this id: " + viewId);
		} else if (viewId >= MAIN_CONTENT_BOUND_MIN && viewId <= MAIN_CONTENT_BOUND_MAX) {
			return resolveMainContent(jsonObject, viewId);
		} else if (viewId >= HEADER_CONTENT_BOUND_MIN && viewId <= HEADER_CONTENT_BOUND_MAX) {
			return resolveHeader(jsonObject, viewId);
		} else if (viewId >= FOOTER_CONTENT_BOUND_MIN && viewId <= FOOTER_CONTENT_BOUND_MAX) {
			return resolveFooter(jsonObject, viewId);
		} else {
			throw new IllegalArgumentException("ID is higher than:" + FOOTER_CONTENT_BOUND_MAX
					+ " No view defined for this id: " + viewId);
		}
	}

	public abstract void fillWithJson(JSONObject jsonObject) throws JSONException;

	private static ViewData resolveHeader(JSONObject jsonObject, int viewId) throws JSONException {
		ViewData data = IdViewMatcher.matchHeader(viewId);
		data.fillWithJson(jsonObject);
		return data;
	}

	private static ViewData resolveMainContent(JSONObject jsonObject, int viewId) throws JSONException {
		ViewData data = IdViewMatcher.matchContent(viewId);
		data.fillWithJson(jsonObject);
		return data;
	}

	private static ViewData resolveFooter(JSONObject jsonObject, int viewId) throws JSONException {
		ViewData data = IdViewMatcher.matchFooter(viewId);
		data.fillWithJson(jsonObject);
		return data;
	}

}
