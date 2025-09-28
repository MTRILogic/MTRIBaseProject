package com.mtrilogic.logic.classes;

import android.util.Log;

@SuppressWarnings("unused")
public class Base {
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;

    private static final String TAG = "MTRILogicTAG";

    public static void makeLog(String line) {
        Log.d(TAG, "makeLog: " + line);
    }
}
