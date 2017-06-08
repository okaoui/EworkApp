package com.developeinjava.android.ework.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developeinjava.android.ework.R;


/**
 * Created by oussamakaoui on 4/1/17.
 */

public class AuthorIdInputFragment extends DialogFragment {
    public static final String EXTRA_AUTHOR_ID = "myproblem.AUTHOR_ID";
    private static final String TAG = "AuthorIdInputFragment";
    int authorId;

    public static AuthorIdInputFragment newInstance() {
        AuthorIdInputFragment fragment = new AuthorIdInputFragment();

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.author_id_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new
                AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
        alertDialogBuilder.setView(v);

        final EditText userInput = (EditText) v
                .findViewById(R.id.author_id_txt);
        alertDialogBuilder
                .setCancelable(false)
                .setTitle(R.string.author_id_dialog_title)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                String input = userInput.getText().toString();
                                try{
                                    if(input.isEmpty())
                                        Toast.makeText(getContext(),R.string.author_id_dialog_no_input,Toast.LENGTH_SHORT).show();
                                    else {
                                        authorId = Integer.parseInt(input);
                                        sendResult(Activity.RESULT_OK);

                                    }

                                }catch(Exception e){
                                    Toast.makeText(getContext(),R.string.author_id_dialog_invalid_id,Toast.LENGTH_SHORT).show();
                                    Log.e(TAG,"parsing failed!",e);
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        return alertDialogBuilder.create();
    }

    private void sendResult(int resultCode){
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_AUTHOR_ID, authorId);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
