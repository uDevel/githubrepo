package com.udevel.network.api;

import com.udevel.data.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by benny_zw on 1/12/2018.
 */

public interface GitHubService {

    String BASE_URL = "https://api.github.com/";

    @GET("repositories")
    Call<List<Repo>> getRepos();
}
