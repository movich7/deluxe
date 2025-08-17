package com.example.launcher.tablet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.launcher.R;
import com.example.launcher.ui.DesignPickerActivity;
import com.example.launcher.util.DesignManager;

public class TabletLauncherActivity extends AppCompatActivity {
	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tablet_host);
		showCurrentDesign();
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_launcher, menu);
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_designs) {
			startActivity(DesignPickerActivity.createIntent(this));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override protected void onResume() {
		super.onResume();
		showCurrentDesign();
	}

	private void showCurrentDesign() {
		DesignManager.Design design = DesignManager.getSelectedDesign(this);
		Fragment fragment;
		switch (design) {
			case DESIGN_2: fragment = new TabletDesign2Fragment(); break;
			case DESIGN_3: fragment = new TabletDesign3Fragment(); break;
			case DESIGN_4: fragment = new TabletDesign4Fragment(); break;
			case DESIGN_5: fragment = new TabletDesign5Fragment(); break;
			case DESIGN_1:
			default: fragment = new TabletDesign1Fragment(); break;
		}
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, fragment, "design")
			.commitAllowingStateLoss();
	}
}