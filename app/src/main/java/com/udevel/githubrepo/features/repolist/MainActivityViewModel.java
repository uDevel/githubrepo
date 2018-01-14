package com.udevel.githubrepo.features.repolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.udevel.network.NetworkService;

import java.util.List;

/**
 * Created by benny_zw on 1/13/2018.
 */

public class MainActivityViewModel extends ViewModel {
    private final GitHubRepoAvatarUrlListLiveData urlListLiveData;

    public MainActivityViewModel(@NonNull NetworkService networkService) {
        urlListLiveData = new GitHubRepoAvatarUrlListLiveData(networkService.getGitHubService());
    }

    public void fetchMoreAvatarUrls() {
        urlListLiveData.fetchNextData();
    }

    public LiveData<List<String>> getUrlListLiveData() {
        return urlListLiveData;
    }
}
