package com.example.launcher.tv;

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

public class TvDesign5Fragment extends Fragment implements TvGridAdapter.OnAppClickListener {
	private final AppRepository repository = new AppRepository();

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.tv_design5, container, false);
		RecyclerView rv = root.findViewById(R.id.recycler);
		rv.setLayoutManager(new GridLayoutManager(requireContext(), 3));
		List<AppInfo> apps = repository.loadLaunchableApps(requireContext(), true, true, getString(R.string.select_design));
		TvGridAdapter adapter = new TvGridAdapter(apps, this);
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