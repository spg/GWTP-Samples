/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.samples.tab.client.application.settings;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.samples.tab.client.application.event.BooleanEvent;

/**
 * The view implementation for {@link com.gwtplatform.samples.tab.client.application.settings.SettingsPresenter} .
 */
public class SettingsView extends ViewWithUiHandlers<SettingsUiHandlers> implements SettingsPresenter.MyView, HasHandlers {
    public interface Binder extends UiBinder<Widget, SettingsView> {
    }

    @UiField
    InlineLabel userPrivileges;
    @UiField
    Button togglePrivileges;
    @UiField
    Button fireTrueButton;
    @UiField
    Button fireFalseButton;

    private final Widget widget;
    private final EventBus eventBus;

    @Inject
    public SettingsView(Binder uiBinder,
            EventBus eventBus) {
        this.eventBus = eventBus;
        widget = uiBinder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public void setAdmin(boolean isAdmin) {
        if (isAdmin) {
            userPrivileges.setText("Admin");
            togglePrivileges.setText("Toggle to non-admin user");
        } else {
            userPrivileges.setText("Non-admin");
            togglePrivileges.setText("Toggle to admin user");
        }
        togglePrivileges.setVisible(true);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    @UiHandler("fireTrueButton")
    void onFireTrueButton(ClickEvent event) {
        BooleanEvent.fire(this, true);
    }

    @UiHandler("fireFalseButton")
    void onFireFalseButton(ClickEvent event) {
        BooleanEvent.fire(this, false);
    }

    @UiHandler("togglePrivileges")
    void onSwitchSecurityClicked(ClickEvent event) {
        getUiHandlers().togglePrivileges();
    }
}
