package com.example.launcher.tablet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.launcher.R;
import com.example.launcher.data.AppRepository;
import com.example.launcher.model.AppInfo;
import com.example.launcher.ui.DesignPickerActivity;

import java.util.List;

public class TabletDesign1Fragment extends Fragment implements AppGridAdapter.OnAppClickListener {
	private final AppRepository repository = new AppRepository();

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.t_design1, container, false);
		RecyclerView rv = root.findViewById(R.id.recycler);
		rv.setLayoutManager(new GridLayoutManager(requireContext(), 5));
		List<AppInfo> apps = repository.loadLaunchableApps(requireContext(), false, true, getString(R.string.select_design));
		AppGridAdapter adapter = new AppGridAdapter(apps, this);
		rv.setAdapter(adapter);
		return root;
	}

	@Override public void onAppClicked(AppInfo appInfo) {
		if (appInfo.isDesignPickerShortcut) {
			startActivity(DesignPickerActivity.createIntent(requireContext()));
			return;
		}
		AppRepository.launchApp(requireContext(), appInfo);
	}
}