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
import android.provider.Settings;
import android.util.AttributeSet;

import androidx.preference.SeekBarPreference;

/** A seek bar preference backed by an int value in {@link Settings.Secure}. */
public class SecureSettingSeekBarPreference extends SeekBarPreference {

    public SecureSettingSeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SecureSettingSeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SecureSettingSeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SecureSettingSeekBarPreference(Context context) {
        super(context);
    }

    @Override
    protected int getPersistedInt(int defaultReturnValue) {
        if (!shouldPersist()) {
            return defaultReturnValue;
        }
        return Settings.Secure.getInt(getContext().getContentResolver(), getKey(),
                defaultReturnValue);
    }

    @Override
    protected boolean persistInt(int value) {
        if (!shouldPersist()) {
            return false;
        }
        if (value == getPersistedInt(~value)) {
            return true;
        }
        Settings.Secure.putInt(getContext().getContentResolver(), getKey(), value);
        return true;
    }
}
