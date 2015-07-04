package cn.car4s.app.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/5/13.
 */
public class DialogUtil {

    public static Dialog buildDialog(Context mContext, View view, int gravity, int animationResId, boolean outsideCancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        AlertDialog dialog = builder.create();
        dialog.show();
        Window window = dialog.getWindow();
        if (gravity >= 0) {
            window.setGravity(gravity);
        }
        if (animationResId > 0) {
            window.setWindowAnimations(animationResId);
        }
        window.setContentView(view);
        dialog.setCanceledOnTouchOutside(outsideCancelable);
        return dialog;
    }


    public static void buildTelDialog(final Context context) {
        if (((Activity) context).isFinishing())
            return;
        final String telnumber = "02150185352";
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("")
                .setMessage("021-50185352")
                .setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        TelephonyUtil.dial(telnumber);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

}
