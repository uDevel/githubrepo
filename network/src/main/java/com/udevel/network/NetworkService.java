package com.udevel.network;

import android.support.annotation.NonNull;

import com.udevel.network.api.GitHubService;

/**
 * Created by benny_zw on 1/13/2018.
 */

public interface NetworkService {
    @NonNull
    GitHubService getGitHubService();
}
