package com.bignerdranch.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import java.io.File;

public class PhotoFragment extends DialogFragment {
    private static final String ARG_PICTURE_PATH = "picture_path";

    private ImageView mImageView;

    public static PhotoFragment newInstance(File photoFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PICTURE_PATH, photoFile);

        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStae) {
        File photoFile = (File) getArguments().getSerializable(ARG_PICTURE_PATH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);

        mImageView = (ImageView) v.findViewById(R.id.dialog_photo_view);
        Bitmap bitmap = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());
        mImageView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dialog_photo)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
    }
}
