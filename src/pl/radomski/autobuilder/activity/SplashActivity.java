package pl.radomski.autobuilder.activity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import pl.radomski.autobuilder.R;
import pl.radomski.autobuilder.utils.AndroidUtils;
import pl.radomski.autobuilder.utils.ResourceDownloader;
import pl.radomski.autobuilder.view.data.ApplicationDescriptionData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SplashActivity extends KrwiodawcaActivity {
	private int sleepTime = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		DownloadFileAsyncTask downloadFileAsyncTask = new DownloadFileAsyncTask();
		downloadFileAsyncTask.execute();

	}

	private class DownloadFileAsyncTask extends AsyncTask<Void, Void, ApplicationDescriptionData> {

		@Override
		protected ApplicationDescriptionData doInBackground(Void... params) {
			ApplicationDescriptionData applicationDescriptionData = null;

			try {
				String string = ResourceDownloader.getStringFromUrl("http://10.0.2.2/~adam/plik.json");
				applicationDescriptionData = ApplicationDescriptionData.createFromJson(new JSONObject(string));
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return applicationDescriptionData;
		}

		@Override
		protected void onPostExecute(ApplicationDescriptionData result) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			((TextView) findViewById(R.id.textView1)).setText(gson.toJson(result));
			PauseAsyncTask asyncTask = new PauseAsyncTask();
			asyncTask.execute();
		}
	}

	private class PauseAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			AndroidUtils.startActivity(SplashActivity.this, FragmentPlacerActivity.class);
			finish();
		}
	}

}
