package pl.radomski.autobuilder.fragment.content.grid;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.radomski.autobuilder.view.data.ViewData;
import android.os.Parcel;
import android.os.Parcelable;

public class GridData extends ViewData {
	private ArrayList<String> texts;

	public GridData() {
		texts = new ArrayList<String>();
	}

	public GridData(Parcel source) {
		texts = new ArrayList<String>();
		source.readStringList(texts);
	}

	@Override
	public void fillWithJson(JSONObject jsonObject) throws JSONException {
		JSONArray jsonArray = jsonObject.getJSONArray("gridData");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject innerObject = jsonArray.getJSONObject(i);
			addText(innerObject.getString("text"));
		}
	}

	public ArrayList<String> getTexts() {
		return texts;
	}

	public void setTexts(ArrayList<String> texts) {
		this.texts = texts;
	}

	public void addText(String string) {
		texts.add(string);
	}

	public static final Creator<GridData> CREATOR = new Parcelable.Creator<GridData>() {

		@Override
		public GridData createFromParcel(Parcel source) {
			return new GridData(source);
		}

		@Override
		public GridData[] newArray(int size) {
			return new GridData[size];
		}

	};

	@Override
	public int describeContents() {
		return GridViewFragment.TYPE_ID;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringList(texts);
	}
}
