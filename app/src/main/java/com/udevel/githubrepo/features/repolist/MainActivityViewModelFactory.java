package com.udevel.githubrepo.features.repolist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udevel.network.NetworkService;

/**
 * Created by benny_zw on 1/13/2018.
 */

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    private final NetworkService networkService;

    MainActivityViewModelFactory(NetworkService networkService) {
        this.networkService = networkService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(networkService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
