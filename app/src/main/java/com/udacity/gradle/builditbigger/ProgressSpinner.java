package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressSpinner {

    private static ProgressDialog mProgressSpinner;

    /**
     * Show a spinner indicating something is happening
     *
     * @param activity activity
     * @param message message to show under spinner
     */
    public static void show(Activity activity, CharSequence message) {
        mProgressSpinner = null;
        if (!activity.isFinishing()) {
            mProgressSpinner = new ProgressDialog(activity);
            mProgressSpinner.setMessage(message);
            mProgressSpinner.show();
        }
    }

    /**
     * Get rid of the progress spinner
     */
    public static void dismiss() {
        if (mProgressSpinner != null && mProgressSpinner.isShowing()) {
            mProgressSpinner.cancel();
        }
        mProgressSpinner = null;
    }
}