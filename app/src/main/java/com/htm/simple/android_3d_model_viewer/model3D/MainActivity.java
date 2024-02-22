package com.htm.simple.android_3d_model_viewer.model3D;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.htm.simple.android_3d_model_viewer.R;
import com.htm.simple.android_3d_model_viewer.model3D.view.MenuActivity;
import com.htm.simple.android_3d_model_viewer.model3D.view.ModelActivity;
import com.htm.android_3d.engine.util.android.AndroidURLStreamHandlerFactory;

import java.net.URL;

/**
 * This is the main android activity. From here we launch the whole stuff.
 * 
 * Basically, this activity may serve to show a Splash screen and copy the assets (obj models) from the jar to external
 * directory.
 * 
 * @author andresoviedo
 *
 */
public class MainActivity extends Activity {


    // Custom handler: org/andresoviedo/app/util/url/android/Handler.class
    static {
        System.setProperty("java.protocol.handler.pkgs", "org.andresoviedo.util.android");
        URL.setURLStreamHandlerFactory(new AndroidURLStreamHandlerFactory());
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set main layout controls.
		// Basically, this is a screen with the app name just in the middle of the scree
		setContentView(R.layout.activity_main);

		// Start Model activity.
		MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), MenuActivity.class));
		MainActivity.this.finish();
	}

	@SuppressWarnings("unused")
	private void init() {
		MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), ModelActivity.class));
		MainActivity.this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
