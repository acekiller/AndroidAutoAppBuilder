package pl.radomski.autobuilder.layout;

import pl.radomski.autobuilder.R;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FrameLayout extends RelativeLayout {

	private LinearLayout header;
	private LinearLayout contentContainer;
	private LinearLayout footer;

	public FrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public FrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FrameLayout(Context context) {
		super(context);
		init();
	}

	private void init() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		inflater.inflate(R.layout.layout_frame, this, true);

		header = (LinearLayout) findViewById(R.id.krwiodawca_header_container);
		contentContainer = (LinearLayout) findViewById(R.id.krwiodawca_content_container);
		footer = (LinearLayout) findViewById(R.id.krwiodawca_footer_container);
	}

	public void setOnHeaderClickListener(OnClickListener clickListener) {
		header.setOnClickListener(clickListener);
	}

	public void setOnFooterClickListener(OnClickListener clickListener) {
		footer.setOnClickListener(clickListener);
	}

	public void placeContent(Fragment fragment) {
		FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.krwiodawca_content_container, fragment);
		fragmentTransaction.commit();
	}

	public void placeHeader(Fragment fragment) {
		FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.krwiodawca_header_container, fragment);
		fragmentTransaction.commit();
	}

	public void placeFooter(Fragment fragment) {
		FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.krwiodawca_footer_container, fragment);
		fragmentTransaction.commit();
	}
}
