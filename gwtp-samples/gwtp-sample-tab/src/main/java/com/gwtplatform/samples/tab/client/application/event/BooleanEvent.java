package com.gwtplatform.samples.tab.client.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BooleanEvent extends GwtEvent<BooleanEvent.BooleanEventHandler> {
    public interface BooleanEventHandler extends EventHandler {
        void onBooleanEvent(BooleanEvent event);
    }

    public static Type<BooleanEventHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, final Boolean bool) {
        source.fireEvent(new BooleanEvent(bool));
    }

    private static final Type<BooleanEventHandler> TYPE = new Type<BooleanEventHandler>();

    private final Boolean bool;

    public BooleanEvent(final Boolean bool) {
        this.bool = bool;
    }

    public Boolean bool() {
        return bool;
    }

    @Override
    public Type<BooleanEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BooleanEventHandler handler) {
        handler.onBooleanEvent(this);
    }
}
