package com.focus.host;

import java.util.List;

import com.focus.comm.Comm;

import dalvik.system.DexClassLoader;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		invokeplugin();
	}

	private void invokeplugin() {
		try {
			Intent intent = new Intent("com.focus.plugin.client");
			PackageManager pm = this.getPackageManager();
			final List<ResolveInfo> plugins = pm.queryIntentActivities(intent,
					0);
			ResolveInfo rinfo = plugins.get(0);
			ActivityInfo ainfo = rinfo.activityInfo;

			String packageName = ainfo.packageName;
			String dexPath = ainfo.applicationInfo.sourceDir;
			String dexOutputDir = this.getDir("dexlib", Context.MODE_PRIVATE)
					.getPath();
			String libPath = ainfo.applicationInfo.nativeLibraryDir;
			Context themeContext = this.createPackageContext(packageName,
					Context.CONTEXT_IGNORE_SECURITY
							+ Context.CONTEXT_INCLUDE_CODE);

			DexClassLoader cl = new DexClassLoader(dexPath, dexOutputDir,
					libPath, this.getClass().getClassLoader());

			Class<?> clazz = cl.loadClass(packageName + ".PluginClass");
			Comm comm = (Comm) clazz.newInstance();

			ViewGroup rootView = (ViewGroup) findViewById(R.id.mainlayout);

			View view = comm.pullView(themeContext, "CircleBattery", rootView);
			view.setBackgroundColor(Color.RED);

			rootView.addView(view);

		} catch (Exception ex) {
			Log.e("Host", "Exception " + ex);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
