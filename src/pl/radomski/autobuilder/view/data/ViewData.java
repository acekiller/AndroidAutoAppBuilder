package pl.radomski.autobuilder.view.data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import pl.radomski.autobuilder.annotations.ContentValue;
import pl.radomski.autobuilder.view.IdViewMatcher;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

	public abstract long insertToDb(SQLiteDatabase db);

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

	public ContentValues getContentValues() throws IllegalArgumentException, IllegalAccessException {
		ArrayList<Field> fields = new ArrayList<Field>();
		for (Field field : getClass().getDeclaredFields()) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof ContentValue) {
					fields.add(field);
				}
			}
		}
		ContentValues contentValues = new ContentValues();

		for (Field field : fields) {
			boolean accesible = field.isAccessible();
			if (!accesible) {
				field.setAccessible(true);
			}
			Class type = field.getType();
			String keyName = field.getAnnotation(ContentValue.class).valueName();
			if (keyName.length() == 0) {
				keyName = field.getName();
			}

			if (type == String.class || type.equals(String.class)) {
				contentValues.put(keyName, (String) field.get(this));
			} else

			if (type.equals(Integer.class) || type.equals(Integer.TYPE) || type.equals(Integer.class)
					|| type.equals(int.class) || type == Integer.class || type == Integer.TYPE || type == Integer.class
					|| type == int.class) {
				contentValues.put(keyName, field.getInt(this));
			}
			field.setAccessible(accesible);
		}

		// Class type = field.getType();
		// String name = field.getName();

		return contentValues;
	}
}
