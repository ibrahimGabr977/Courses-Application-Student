package com.hope.igb.aic_student.common.views;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseObservableMvc<ListenerType> extends BaseViewMvc implements
        ObservableViewMvc<ListenerType> {

//    private final Set<ListenerType> listeners = new HashSet<>(1);
    // thread-safe set of listeners
    private final Set<ListenerType> listeners = Collections.newSetFromMap(
            new ConcurrentHashMap<ListenerType, Boolean>(1));


    @Override
    public void registerListener(ListenerType listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(ListenerType listener) {
        listeners.remove(listener);
    }

    protected Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }



}
