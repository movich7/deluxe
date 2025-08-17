package com.example.launcher.tv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.launcher.R;
import com.example.launcher.model.AppInfo;

import java.util.List;

public class TvGridAdapter extends RecyclerView.Adapter<TvGridAdapter.VH> {
	public interface OnAppClickListener {
		void onAppClicked(AppInfo appInfo);
	}

	private final List<AppInfo> appList;
	private final OnAppClickListener listener;

	public TvGridAdapter(List<AppInfo> appList, OnAppClickListener listener) {
		this.appList = appList;
		this.listener = listener;
	}

	@NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_app, parent, false);
		return new VH(view);
	}

	@Override public void onBindViewHolder(@NonNull VH holder, int position) {
		AppInfo app = appList.get(position);
		holder.title.setText(app.label);
		if (app.icon != null) holder.icon.setImageDrawable(app.icon);
		else holder.icon.setImageResource(R.drawable.ic_placeholder);
		holder.itemView.setOnClickListener(v -> listener.onAppClicked(app));
	}

	@Override public int getItemCount() { return appList.size(); }

	static class VH extends RecyclerView.ViewHolder {
		ImageView icon;
		TextView title;
		VH(@NonNull View itemView) {
			super(itemView);
			icon = itemView.findViewById(R.id.icon);
			title = itemView.findViewById(R.id.title);
		}
	}
}