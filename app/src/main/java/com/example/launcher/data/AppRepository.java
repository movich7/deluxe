package com.example.launcher.data;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.example.launcher.model.AppInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppRepository {
	public List<AppInfo> loadLaunchableApps(Context context, boolean forTv, boolean includeDesignShortcut, String designShortcutLabel) {
		PackageManager pm = context.getPackageManager();
		Set<String> seenComponents = new HashSet<>();
		List<AppInfo> result = new ArrayList<>();

		if (includeDesignShortcut) {
			result.add(new AppInfo("__internal__", null, designShortcutLabel, null, true, true));
		}

		// Primary query for Leanback on TV
		List<ResolveInfo> leanback = new ArrayList<>();
		if (forTv) {
			Intent tvIntent = new Intent(Intent.ACTION_MAIN);
			tvIntent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
			leanback.addAll(pm.queryIntentActivities(tvIntent, 0));
		}

		// General Android launcher category
		Intent phoneIntent = new Intent(Intent.ACTION_MAIN);
		phoneIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> launcher = pm.queryIntentActivities(phoneIntent, 0);

		List<ResolveInfo> combined = new ArrayList<>();
		combined.addAll(leanback);
		combined.addAll(launcher);

		for (ResolveInfo info : combined) {
			ActivityInfo ai = info.activityInfo;
			if (ai == null) continue;
			String compKey = ai.packageName + "/" + ai.name;
			if (seenComponents.contains(compKey)) continue;
			seenComponents.add(compKey);
			boolean lb = (info.filter != null && info.filter.hasCategory(Intent.CATEGORY_LEANBACK_LAUNCHER));
			try {
				CharSequence labelCs = ai.loadLabel(pm);
				String label = labelCs != null ? labelCs.toString() : ai.packageName;
				result.add(new AppInfo(
					ai.packageName,
					ai.name,
					label,
					ai.loadIcon(pm),
					lb
				));
			} catch (Exception ignore) {
			}
		}

		Collections.sort(result, new Comparator<AppInfo>() {
			@Override public int compare(AppInfo a, AppInfo b) {
				if (a.isDesignPickerShortcut && !b.isDesignPickerShortcut) return -1;
				if (!a.isDesignPickerShortcut && b.isDesignPickerShortcut) return 1;
				return a.label.compareToIgnoreCase(b.label);
			}
		});

		return result;
	}

	public static void launchApp(Context context, AppInfo appInfo) {
		if (appInfo.isDesignPickerShortcut) return;
		try {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
			if (appInfo.className != null) {
				intent.setComponent(new ComponentName(appInfo.packageName, appInfo.className));
			} else {
				intent.setPackage(appInfo.packageName);
			}
			context.startActivity(intent);
		} catch (Exception ignored) {
		}
	}
}