package com.cylonid.nativealpha;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

public final class Utility {

    public static Intent createWebViewIntent(WebApp d, Context c) {
        Intent intent = new Intent(c, WebViewActivity.class);
        intent.putExtra(Const.INTENT_WEBAPPID, d.getID());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setAction(Intent.ACTION_VIEW);

        return intent;
    }

    public static Long getTimeInSeconds()
    {
        return System.currentTimeMillis() / 1000;
    }

    public static void Assert(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void personalizeToolbar(AppCompatActivity a)  {
        Toolbar toolbar = a.findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.native_alpha_white);
        toolbar.setTitle(R.string.app_name);
        a.setSupportActionBar(toolbar);
    }

    public static Integer getWidthFromIcon(String size_string) {
        int x_index = size_string.indexOf("x");
        if (x_index == -1)
            x_index = size_string.indexOf("×");

        if (x_index == -1)
            return 1;
        String width = size_string.substring(0, x_index);

        return Integer.parseInt(width);
    }


    public static void applyUITheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int id = DataManager.getInstance().getSettings().getThemeId();
            switch (id) {
                case 0:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    break;
                case 1:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case 2:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
            }
        }
    }

    @ColorInt
    public static int getThemeColor
            (
                    @NonNull final Context context,
                    @AttrRes final int attributeColor
            )
    {
        final TypedValue value = new TypedValue();
        context.getTheme ().resolveAttribute (attributeColor, value, true);
        return value.data;
    }

}
