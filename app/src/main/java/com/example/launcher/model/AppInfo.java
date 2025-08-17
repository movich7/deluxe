package com.example.launcher.model;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppInfo {
	public final String packageName;
	@Nullable public final String className;
	public final String label;
	@Nullable public final Drawable icon;
	public final boolean leanbackEnabled;
	public final boolean isDesignPickerShortcut;

	public AppInfo(String packageName, @Nullable String className, String label, @Nullable Drawable icon, boolean leanbackEnabled) {
		this(packageName, className, label, icon, leanbackEnabled, false);
	}

	public AppInfo(String packageName, @Nullable String className, String label, @Nullable Drawable icon, boolean leanbackEnabled, boolean isDesignPickerShortcut) {
		this.packageName = packageName;
		this.className = className;
		this.label = label;
		this.icon = icon;
		this.leanbackEnabled = leanbackEnabled;
		this.isDesignPickerShortcut = isDesignPickerShortcut;
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AppInfo)) return false;
		AppInfo that = (AppInfo) o;
		return packageName.equals(that.packageName) && ((className == null && that.className == null) || (className != null && className.equals(that.className)));
	}

	@Override public int hashCode() {
		int result = packageName.hashCode();
		result = 31 * result + (className != null ? className.hashCode() : 0);
		return result;
	}

	@Override @NonNull public String toString() {
		return label + " (" + packageName + ")";
	}
}