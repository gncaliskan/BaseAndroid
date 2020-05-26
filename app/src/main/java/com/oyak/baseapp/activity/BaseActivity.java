package com.oyak.baseapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oyak.baseapp.constant.SystemConstants;
import com.oyak.baseapp.MainActivity;
import com.oyak.baseapp.R;
import com.oyak.baseapp.util.SharedUtil;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void goBack(Activity activity) {
        ImageView back = activity.findViewById(R.id.title_main_back);
        if (back != null) {
            back.setOnClickListener(v -> onBackPressed());
        }
    }

    protected void setNotifyButton(Activity activity) {
        ImageView notify = activity.findViewById(R.id.actionbar_notify);
        if (SharedUtil.getDefaults(SystemConstants.SHARED_USER, activity) != null) {
            if (notify != null) {
                notify.setVisibility(View.VISIBLE);
                //TODO Bildirimler için activity yaratılınca oraya yönlendirilecek
                notify.setOnClickListener(v -> startActivity(new Intent(activity, MainActivity.class)));
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    focusedView.getWindowToken(), 0);
        }
    }

    void setupUI(View view, final Activity activity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyboard(activity);
                return false;
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public abstract void setLayouts();


}
