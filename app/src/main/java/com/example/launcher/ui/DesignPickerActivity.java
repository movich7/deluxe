package com.example.launcher.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.launcher.R;
import com.example.launcher.util.DesignManager;

public class DesignPickerActivity extends Activity implements View.OnClickListener {
	public static Intent createIntent(Context context) {
		return new Intent(context, DesignPickerActivity.class);
	}

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design_picker);
		findViewById(R.id.btn_d1).setOnClickListener(this);
		findViewById(R.id.btn_d2).setOnClickListener(this);
		findViewById(R.id.btn_d3).setOnClickListener(this);
		findViewById(R.id.btn_d4).setOnClickListener(this);
		findViewById(R.id.btn_d5).setOnClickListener(this);
	}

	@Override public void onClick(View v) {
		int id = v.getId();
		DesignManager.Design design = DesignManager.Design.DESIGN_1;
		if (id == R.id.btn_d1) design = DesignManager.Design.DESIGN_1;
		else if (id == R.id.btn_d2) design = DesignManager.Design.DESIGN_2;
		else if (id == R.id.btn_d3) design = DesignManager.Design.DESIGN_3;
		else if (id == R.id.btn_d4) design = DesignManager.Design.DESIGN_4;
		else if (id == R.id.btn_d5) design = DesignManager.Design.DESIGN_5;
		DesignManager.setSelectedDesign(this, design);
		Toast.makeText(this, getString(R.string.select_design) + ": " + design.name().replace('_',' '), Toast.LENGTH_SHORT).show();
		finish();
	}
}