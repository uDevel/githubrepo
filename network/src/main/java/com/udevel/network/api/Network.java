package com.udevel.network.api;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by benny_zw on 1/12/2018.
 */

public class Network {

    @NonNull
    public static GitHubService getGitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GitHubService.class);
    }
}
