package com.focus.plugin;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class PluginClass implements com.focus.comm.Comm {

	public PluginClass() {
		// TODO Auto-generated constructor stub
		Log.i("Plugin", "PluginClass client initialized");
	}

	@Override
	public View pullView(Context context, String viewName) {
		// TODO Auto-generated method stub
		return pullView(context, viewName, null);
	}

	@Override
	public View pullView(Context context, String viewName, ViewGroup root) {
		// TODO Auto-generated method stub
		viewName = viewName.toLowerCase();
		String packageName = context.getPackageName();
		int id = context.getResources().getIdentifier(viewName, "layout",
				packageName);
		if (id == 0)
			return null;
		return View.inflate(context, id, root);
	}

}
