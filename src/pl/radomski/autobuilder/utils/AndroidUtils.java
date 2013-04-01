package pl.radomski.autobuilder.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class AndroidUtils {

	public static void startActivity(Context context, Class<? extends Activity> clazz) {
		Intent intent = new Intent();
		intent.setClass(context, clazz);
		context.startActivity(intent);
	}

}
