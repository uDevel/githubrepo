package com.udevel.githubrepo.features.repolist;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.udevel.data.github.Repo;
import com.udevel.network.api.GitHubService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by benny_zw on 1/13/2018.
 */

public class GitHubRepoAvatarUrlListLiveData extends LiveData<List<String>> implements Callback<List<Repo>> {
    @NonNull
    private final GitHubService gitHubService;
    private Call<List<Repo>> serviceCall;
    private int lastFetchedId = 0;

    public GitHubRepoAvatarUrlListLiveData(@NonNull GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        serviceCall = null;

        if (response.isSuccessful()) {
            List<Repo> repoList = response.body();
            if (repoList != null) {
                Set<String> avatarUrlSet = new HashSet<>(repoList.size());

                for (Repo repo : repoList) {
                    avatarUrlSet.add(repo.getOwner().getAvatarUrl());
                }

                List<String> existingUrlList = getValue();

                if (existingUrlList == null) {
                    existingUrlList = new ArrayList<>(avatarUrlSet.size());
                }
                existingUrlList.addAll(avatarUrlSet);

                setValue(existingUrlList);

                Integer id = repoList.get(repoList.size() - 1).getId();
                if (id != null) {
                    lastFetchedId = id;
                }
            }
        }
    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
        serviceCall = null;
    }

    public void fetchNextData() {
        if (serviceCall != null && serviceCall.isExecuted()) {
            // Already fetching.
            return;
        }

        serviceCall = gitHubService.getRepos(lastFetchedId);
        serviceCall.enqueue(this);
    }

    private void cancelCurrentFetch() {
        if (serviceCall != null && !serviceCall.isCanceled()) {
            serviceCall.cancel();
            serviceCall = null;
        }
    }
}
