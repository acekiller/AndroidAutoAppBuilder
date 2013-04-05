package pl.radomski.autobuilder.activity;

import pl.radomski.autobuilder.R;
import pl.radomski.autobuilder.layout.FrameLayout;
import android.os.Bundle;

public class FrameActivity extends EasyActivity {

	public static final String TAG = FrameActivity.class.getSimpleName();

	private FrameLayout krwiodawcaLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame);
		krwiodawcaLayout = (FrameLayout) findViewById(R.id.content);
	}

	public FrameLayout getKrwiodawcaLayout() {
		return krwiodawcaLayout;
	}
}
