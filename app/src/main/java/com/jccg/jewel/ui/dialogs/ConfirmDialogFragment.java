package com.jccg.jewel.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class ConfirmDialogFragment extends DialogFragment {

    /**
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Delete Jewel")
                .setMessage("")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    /**
                     *
                     * @param dialogo
                     * @param id
                     */
                    @Override
                    public void onClick(DialogInterface dialogo, int id) {

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    /**
                     *
                     * @param dialogo
                     * @param id
                     */
                    @Override
                    public void onClick(DialogInterface dialogo, int id) {

                    }
                });

        return builder.create();
    }

}
