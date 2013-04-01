package pl.radomski.autobuilder.view.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApplicationDescriptionData {
	private static final String JSON_DATA = "data";
	private static final String JSON_FILE_VERSION = "fileVersion";
	private int version;
	private ArrayList<ViewDescriptionData> descriptionDatas;

	public static ApplicationDescriptionData createFromJson(JSONObject jsonObject) throws JSONException {
		ApplicationDescriptionData applicationDescriptionData = new ApplicationDescriptionData();
		applicationDescriptionData.setVersion(jsonObject.getInt(JSON_FILE_VERSION));
		JSONArray data = jsonObject.getJSONArray(JSON_DATA);

		for (int i = 0; i < data.length(); i++) {
			JSONObject object = data.getJSONObject(i);
			applicationDescriptionData.addDescriptionDatas(ViewDescriptionData.createFromJson(object));
		}

		return applicationDescriptionData;
	}

	public ApplicationDescriptionData() {
		setDescriptionDatas(new ArrayList<ViewDescriptionData>());
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ArrayList<ViewDescriptionData> getDescriptionDatas() {
		return descriptionDatas;
	}

	public void setDescriptionDatas(ArrayList<ViewDescriptionData> descriptionDatas) {
		this.descriptionDatas = descriptionDatas;
	}

	public void addDescriptionDatas(ViewDescriptionData descriptionData) {
		descriptionDatas.add(descriptionData);
	}

}
