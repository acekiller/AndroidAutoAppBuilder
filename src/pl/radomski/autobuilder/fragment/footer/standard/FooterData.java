package pl.radomski.autobuilder.fragment.footer.standard;

import org.json.JSONException;
import org.json.JSONObject;

import pl.radomski.autobuilder.view.data.ViewData;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

public class FooterData extends ViewData {
	public static final String TABLE_NAME = "footer";

	public static final String COLUMN_NAME_ID = TABLE_NAME + "." + "id";
	private int id;

	public static final String COLUMN_NAME_TITLE = TABLE_NAME + "." + "title";
	private String title;

	public FooterData() {
	}

	public FooterData(Parcel source) {
		title = source.readString();
	}

	@Override
	public void fillWithJson(JSONObject jsonObject) throws JSONException {
		setTitle(jsonObject.getString("title"));
	}

	@Override
	public long insertToDb(SQLiteDatabase db) {
		return 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static final Creator<FooterData> CREATOR = new Parcelable.Creator<FooterData>() {

		@Override
		public FooterData createFromParcel(Parcel source) {
			return new FooterData(source);
		}

		@Override
		public FooterData[] newArray(int size) {
			return new FooterData[size];
		}

	};

	@Override
	public int describeContents() {
		return FooterFragment.TYPE_ID;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
	}

	public static class FooterValuesBuilder {
		private ContentValues values = new ContentValues();

		public FooterValuesBuilder addTitle(String value) {
			values.put(COLUMN_NAME_TITLE, value);
			return this;
		}

		public ContentValues build() {
			return values;
		}
	}

}
