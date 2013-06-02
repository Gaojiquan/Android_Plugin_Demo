package com.focus.comm;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface Comm {

	public View pullView(Context context, String viewName);

	public View pullView(Context context, String viewName, ViewGroup root);
}
