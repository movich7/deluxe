package com.example.launcher.util;

import android.content.Context;
import android.content.SharedPreferences;

public final class DesignManager {
	public enum Design {
		DESIGN_1(1),
		DESIGN_2(2),
		DESIGN_3(3),
		DESIGN_4(4),
		DESIGN_5(5);

		public final int id;

		Design(int id) { this.id = id; }

		public static Design fromId(int id) {
			for (Design d : values()) {
				if (d.id == id) return d;
			}
			return DESIGN_1;
		}
	}

	private static final String PREFS = "design_prefs";
	private static final String KEY_SELECTED = "selected_design";

	private DesignManager() {}

	public static Design getSelectedDesign(Context context) {
		SharedPreferences sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
		int id = sp.getInt(KEY_SELECTED, Design.DESIGN_1.id);
		return Design.fromId(id);
	}

	public static void setSelectedDesign(Context context, Design design) {
		context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
			.edit()
			.putInt(KEY_SELECTED, design.id)
			.apply();
	}
}