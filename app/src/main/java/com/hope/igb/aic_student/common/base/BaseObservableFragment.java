package com.hope.igb.aic_student.common.base;

import androidx.fragment.app.Fragment;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BaseObservableFragment<LISTENER> extends Fragment {
    private final Set<LISTENER> listeners = Collections.newSetFromMap(
            new ConcurrentHashMap<>(1));


    protected Set<LISTENER> getListeners() {
        return listeners;
    }

    public void registerFragmentListener(LISTENER listener){
        listeners.add(listener);
    }

    public void unregisterFragmentListener(LISTENER listener){
        listeners.remove(listener);
    }
}
