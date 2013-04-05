package pl.radomski.autobuilder.fragment.header.standard;

import org.json.JSONException;
import org.json.JSONObject;

import pl.radomski.autobuilder.annotations.ContentValue;
import pl.radomski.autobuilder.view.data.ViewData;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

public class HeaderData extends ViewData {
	public static final String TABLE_NAME = "header";

	public static final String COLUMN_NAME_ID = TABLE_NAME + "." + "id";
	@ContentValue(valueName = "AJDI")
	private int id;

	public static final String COLUMN_NAME_TITLE = TABLE_NAME + "." + "title";
	@ContentValue(valueName = "dupa")
	private String title;

	@Override
	public void fillWithJson(JSONObject jsonObject) throws JSONException {
		setTitle(jsonObject.getString("title"));
	}

	@Override
	public long insertToDb(SQLiteDatabase db) {
		return 0;
	}

	public HeaderData() {
	}

	public HeaderData(Parcel source) {
		title = source.readString();
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

	public static final Creator<HeaderData> CREATOR = new Parcelable.Creator<HeaderData>() {

		@Override
		public HeaderData createFromParcel(Parcel source) {
			return new HeaderData(source);
		}

		@Override
		public HeaderData[] newArray(int size) {
			return new HeaderData[size];
		}

	};

	@Override
	public int describeContents() {
		return HeaderFragment.TYPE_ID;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
	}

	public static class HeaderValuesBuilder {
		private ContentValues values = new ContentValues();

		public HeaderValuesBuilder addTitle(String value) {
			values.put(COLUMN_NAME_TITLE, value);
			return this;
		}

		public ContentValues build() {
			return values;
		}
	}

}
