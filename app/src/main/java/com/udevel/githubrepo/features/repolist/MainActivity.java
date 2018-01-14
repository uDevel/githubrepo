package com.udevel.githubrepo.features.repolist;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.udevel.githubrepo.R;
import com.udevel.network.NetworkServiceImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Observer<List<String>> {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerViewList;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViews();
        setupViewModel();
    }

    @Override
    public void onChanged(@Nullable List<String> avatarUrlList) {
        if (avatarUrlList == null) {
            return;
        }

        ImageListAdapter adapter = (ImageListAdapter) recyclerViewList.getAdapter();
        if (adapter == null) {
            adapter = new ImageListAdapter(Glide.with(MainActivity.this));
            recyclerViewList.setAdapter(adapter);
        }

        adapter.updateData(avatarUrlList);
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(new NetworkServiceImpl())).get(MainActivityViewModel.class);
        viewModel.fetchMoreAvatarUrls();
        viewModel.getUrlListLiveData().observe(this, this);
    }

    private void setupViews() {
        setContentView(R.layout.activity_main);

        recyclerViewList = findViewById(R.id.recycler_view_list);
        recyclerViewList.setLayoutManager(new GridLayoutManager(this, 5));
    }
}
