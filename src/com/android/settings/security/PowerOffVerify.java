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

package com.android.settings.security;

import android.app.settings.SettingsEnums;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

/** Allows requiring the screen lock credential before the device can be powered off. */
public class PowerOffVerify extends SettingsPreferenceFragment {

    private static final String KEY_POWER_OFF_VERIFY = "power_off_verify";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = requireContext();
        final PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

        final SwitchPreferenceCompat preference = new SwitchPreferenceCompat(context);
        preference.setKey(KEY_POWER_OFF_VERIFY);
        preference.setTitle(R.string.power_off_verify_title);
        preference.setSummary(R.string.power_off_verify_summary);
        preference.setChecked(Settings.Secure.getInt(context.getContentResolver(),
                KEY_POWER_OFF_VERIFY, 0) == 1);
        preference.setOnPreferenceChangeListener((pref, newValue) -> {
            Settings.Secure.putInt(context.getContentResolver(), KEY_POWER_OFF_VERIFY,
                    (Boolean) newValue ? 1 : 0);
            return true;
        });
        screen.addPreference(preference);

        setPreferenceScreen(screen);
    }

    @Override
    public int getMetricsCategory() {
        return SettingsEnums.SECURITY;
    }
}
