package com.br.desafiozup.ui.components;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextAnimationDrawableEditext implements TextWatcher {

    private EditText editText;
    private boolean isModeEdit;
    private Context context;

    public TextAnimationDrawableEditext(EditText editText, Context context) {
        this.editText = editText;
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().length() > 0 && !isModeEdit) {
            isModeEdit = true;

            Drawable[] myTextViewCompoundDrawables = editText.getCompoundDrawables();

            for (Drawable drawable : myTextViewCompoundDrawables) {
                if (drawable == null)
                    continue;

                fadIn(drawable);
            }

        } else if (s.toString().length() < 1) {
            isModeEdit = false;
            Drawable[] myTextViewCompoundDrawables = editText.getCompoundDrawables();

            for (Drawable drawable : myTextViewCompoundDrawables) {
                {

                    if (drawable == null)
                        continue;

                    fadOut(drawable);
                }
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private void fadIn(Drawable drawable) {
        AnimateView(drawable, 255);
    }

    private void fadOut(Drawable drawable) {
        AnimateView(drawable, 0);
    }

    private void AnimateView(Drawable drawable, int value) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", value));
        animator.setTarget(drawable);
        animator.setDuration(500);
        animator.start();
    }
}
