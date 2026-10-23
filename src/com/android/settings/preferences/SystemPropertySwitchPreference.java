/*
 * Copyright (C) 2025 AxionOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.preferences;

import android.content.Context;
import android.os.SystemProperties;
import android.util.AttributeSet;

import androidx.preference.SwitchPreferenceCompat;

/** A switch preference backed by a boolean system property named after its key. */
public class SystemPropertySwitchPreference extends SwitchPreferenceCompat {

    public SystemPropertySwitchPreference(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SystemPropertySwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SystemPropertySwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SystemPropertySwitchPreference(Context context) {
        super(context);
    }

    @Override
    protected boolean getPersistedBoolean(boolean defaultReturnValue) {
        if (!shouldPersist()) {
            return defaultReturnValue;
        }
        return SystemProperties.getBoolean(getKey(), defaultReturnValue);
    }

    @Override
    protected boolean persistBoolean(boolean value) {
        if (!shouldPersist()) {
            return false;
        }
        if (value == getPersistedBoolean(!value)) {
            return true;
        }
        SystemProperties.set(getKey(), value ? "1" : "0");
        return true;
    }
}
