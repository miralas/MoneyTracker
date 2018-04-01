package com.miralas.moneytracker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ActionMode;

import static android.content.ContentValues.TAG;

/**
 * Created by tiburon on 31/03/2018.
 */

public class ConfirmationDialog extends DialogFragment {


    private ConfirmationDialogListener listener = null;

    public void setListener(ConfirmationDialogListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Add listeners like in ItemsAdapter
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_question)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onPositiveButtonClick();
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onNegativeButtonClick();
                        }
                    }
                })
                .create();

        return dialog;


    }
}
