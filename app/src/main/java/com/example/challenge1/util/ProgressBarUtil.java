package com.example.challenge1.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.example.challenge1.R;

public class ProgressBarUtil {

    private Context context;

    public ProgressBarUtil(Context context) {
        this.context = context;
    }

    public void setDialog(boolean show){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //View view = getLayoutInflater().inflate(R.layout.progress);
        builder.setView(R.layout.activity_main);
        Dialog dialog = builder.create();
        if (show)dialog.show();
        else dialog.dismiss();
    }
}
