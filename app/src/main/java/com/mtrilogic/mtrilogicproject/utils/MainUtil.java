package com.mtrilogic.mtrilogicproject.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.mtrilogic.mtrilogicproject.R;

public class MainUtil {
    private static final Fruit[] FRUITS = {
            new Fruit("orange", R.raw.orange64),
            new Fruit("watermelon", R.raw.watermelon64),
            new Fruit("pineapple", R.raw.pineapple64),
            new Fruit("cherry", R.raw.cherry64),
            new Fruit("pear", R.raw.pear64),
            new Fruit("strawberry", R.raw.strawberry64),
            new Fruit("banana", R.raw.banana64),
            new Fruit("grapess", R.raw.grapes64),
            new Fruit("apple", R.raw.apple64),
            new Fruit("peach", R.raw.peach64)
    };

    private static final Drawable[] DRAWABLES = new Drawable[10];

    public static void initializeDrawables(Context context) {
        for (int i = 0; i < FRUITS.length; i++) {
            DRAWABLES[i] = ResourcesCompat.getDrawable(context.getResources(), FRUITS[i].resId, context.getTheme());
        }
    }

    public static Drawable getDrawable(int index) {
        return DRAWABLES[index];
    }

    public static String getName(int index) {
        return FRUITS[index].name;
    }

    private record Fruit(String name, int resId) {
    }
}
