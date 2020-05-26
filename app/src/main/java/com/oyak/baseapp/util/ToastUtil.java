package com.oyak.baseapp.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oyak.baseapp.R;


public class ToastUtil {
    public static final String WARNING = "toast_warning";
    public static final String ERROR = "toast_error";
    public static String INFO = "toast_info";
    private static final String SUCCESS = "toast_success";


    public static void show(Activity activity, int message, String type){
        show(activity, activity.getResources().getString(message), type);
    }

    public static void show(Activity activity, String message, String type){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_message, activity.findViewById(R.id.toast_layout_root));

        if(type.equals(ERROR)){
            layout.setBackground(activity.getDrawable(R.drawable.shape_error));
        }else if(type.equals(WARNING)){
            layout.setBackground(activity.getDrawable(R.drawable.shape_warning));
        }else{
            layout.setBackground(activity.getDrawable(R.drawable.shape_info));
        }

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
