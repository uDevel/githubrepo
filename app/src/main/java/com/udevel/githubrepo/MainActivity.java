package com.udevel.githubrepo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.udevel.data.github.Repo;
import com.udevel.network.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView textView;
    private ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.test);
        imageViewAvatar = findViewById(R.id.avatar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Call<List<Repo>> call = NetworkService.getGitHubService().getRepos();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    List<Repo> repoList = response.body();
                    if (repoList != null) {
                        Repo repo = repoList.get(0);
                        textView.setText(repo.getOwner().getLogin());
                        Glide.with(imageViewAvatar).load(repo.getOwner().getAvatarUrl()).into(imageViewAvatar);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
