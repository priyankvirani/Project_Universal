package com.project.universal.utlis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.project.universal.R;


public class Utils {
    private static final String TAG = Utils.class.getSimpleName();
    /**
     * Check the device to make sure it has the Google Play Services APK. If it
     * doesn't, display a dialog that allows users to download the APK from the
     * Google Play Store or enable it in the device's system settings.
     */
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    // dp to px converter
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    // px to dp converter
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static Drawable getDrawable(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    public static boolean isNullOrEmpty(String tmpStr) {
        return !(tmpStr != null && !tmpStr.isEmpty());
    }

    public static boolean isValidEmail(String tmpStr) {
        if (isNullOrEmpty(tmpStr)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(tmpStr)
                    .matches();
        }
    }

    /**
     * Check for internet connection
     */
    public static boolean isInternetAvailable(Context context,
                                              boolean isShowAlert) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            // Toast.makeText(context, "Net", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            if (isShowAlert) {
                showToastShort(context,
                        context.getString(R.string.error_internet_check));
                // Toast.makeText(context,
                // context.getString(R.string.error_internet_check),
                // Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    /**
     * To display alerts
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showAlert(Context context, String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message).setCancelable(false)
                .setPositiveButton(context.getString(R.string.ok), null);

        Dialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    /**
     * Share with other apps
     */
    public static void share(Context context, String message) {
        Intent sharingIntent = new Intent();
        sharingIntent.setAction(Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, message);
        sharingIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sharingIntent,
                "Share using : "));
    }

//    public static void copyTextToClipBoard(Context context, String copiedText) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
//            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//            clipboard.setText(copiedText);
//            showToastShort(context, context.getResources().getString(R.string.msg_copy_clipboard));
//        } else {
//            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", copiedText);
//            clipboard.setPrimaryClip(clip);
//            showToastShort(context, context.getResources().getString(R.string.msg_copy_clipboard));
//        }
//    }

    public static void hideSoftKeyBoard(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    public static int getDeviceWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.d(TAG, "dm.widthPixels = " + dm.widthPixels);
        return dm.widthPixels;
    }

}
