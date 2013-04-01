package pl.radomski.autobuilder.view.data;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewDescriptionData {

	private static final String JSON_FOOTER_CONTENT = "footerContent";
	private static final String JSON_HEADER_CONTENT = "headerContent";
	private static final String JSON_MAIN_CONTENT = "mainContent";
	private static final String JSON_FOOTER_TYPE = "footerType";
	private static final String JSON_HEADER_TYPE = "headerType";
	private static final String JSON_TYPE = "type";
	public static final String JSON_VIEW_ID = "viewId";

	private int viewId;
	private int type;
	private ViewData mainData;
	private int headerType;
	private ViewData headerData;
	private int footerType;
	private ViewData footerData;

	public static ViewDescriptionData createFromJson(JSONObject object) throws JSONException {
		ViewDescriptionData descriptionData = new ViewDescriptionData();
		descriptionData.setViewId(object.getInt(JSON_VIEW_ID));

		descriptionData.setType(object.getInt(JSON_TYPE));
		descriptionData.setMainData(ViewData.createFromJson(object.getJSONObject(JSON_MAIN_CONTENT),
				descriptionData.getType()));

		descriptionData.setHeaderType(object.getInt(JSON_HEADER_TYPE));
		descriptionData.setHeaderData(ViewData.createFromJson(object.getJSONObject(JSON_HEADER_CONTENT),
				descriptionData.getHeaderType()));

		descriptionData.setFooterType(object.getInt(JSON_FOOTER_TYPE));
		descriptionData.setFooterData(ViewData.createFromJson(object.getJSONObject(JSON_FOOTER_CONTENT),
				descriptionData.getFooterType()));

		return descriptionData;
	}

	public ViewDescriptionData() {

	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ViewData getMainData() {
		return mainData;
	}

	public void setMainData(ViewData mainData) {
		this.mainData = mainData;
	}

	public int getHeaderType() {
		return headerType;
	}

	public void setHeaderType(int headerType) {
		this.headerType = headerType;
	}

	public ViewData getHeaderData() {
		return headerData;
	}

	public void setHeaderData(ViewData headerData) {
		this.headerData = headerData;
	}

	public int getFooterType() {
		return footerType;
	}

	public void setFooterType(int footerType) {
		this.footerType = footerType;
	}

	public ViewData getFooterData() {
		return footerData;
	}

	public void setFooterData(ViewData footerData) {
		this.footerData = footerData;
	}

}
