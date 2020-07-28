package com.thoughtworks.android.startkit.util;

import androidx.lifecycle.LiveData;

public class AbsentLiveData<T> extends LiveData<T> {

    private AbsentLiveData() {
        postValue(null);
    }

    public static <T> LiveData<T> create() {
        return new AbsentLiveData<>();
    }
}
