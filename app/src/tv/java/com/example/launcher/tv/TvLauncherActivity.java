package com.example.launcher.tv;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;

import com.example.launcher.R;
import com.example.launcher.util.DesignManager;

public class TvLauncherActivity extends FragmentActivity {
	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tv_host);
		showCurrentDesign();
	}

	@Override protected void onResume() {
		super.onResume();
		showCurrentDesign();
	}

	private void showCurrentDesign() {
		DesignManager.Design design = DesignManager.getSelectedDesign(this);
		Fragment fragment;
		switch (design) {
			case DESIGN_2: fragment = new TvDesign2Fragment(); break;
			case DESIGN_3: fragment = new TvDesign3Fragment(); break;
			case DESIGN_4: fragment = new TvDesign4Fragment(); break;
			case DESIGN_5: fragment = new TvDesign5Fragment(); break;
			case DESIGN_1:
			default: fragment = new TvDesign1Fragment(); break;
		}
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, fragment, "design")
			.commitAllowingStateLoss();
	}
}