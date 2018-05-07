package com.thoughtworks.android.startkit.wrapper;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Event<T> {
    private boolean hasBeenhandled = false;

    @Getter
    @Setter
    @NonNull
    private T content;

    public T getContentIfNotHandled() {
        if (hasBeenhandled) {
            return null;
        }
        hasBeenhandled = true;
        return content;
    }
}
