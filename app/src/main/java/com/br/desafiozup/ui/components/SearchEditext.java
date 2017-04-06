package com.br.desafiozup.ui.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;

public class SearchEditext extends android.support.v7.widget.AppCompatEditText {

    private Drawable drawableRight;
    private Drawable drawableLeft;
    private Drawable drawableTop;
    private Drawable drawableBottom;
    private DrawableClickListener clickListener;
    int actionX, actionY;

    public SearchEditext(Context context) {
        super(context);
    }

    public SearchEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchEditext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        setImeOptions(EditorInfo.IME_ACTION_DONE);

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {

        if (left != null) {
            drawableLeft = left;
        }
        if (right != null) {
            drawableRight = right;
        }
        if (top != null) {
            drawableTop = top;
        }
        if (bottom != null) {
            drawableBottom = bottom;
        }

        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        Rect bounds;

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            actionX = (int) e.getX();
            actionY = (int) e.getY();

            if (drawableBottom != null
                    && drawableBottom.getBounds().contains(actionX, actionY)) {
                clickListener.onClick(DrawablePosition.BOTTOM);
                super.onTouchEvent(e);
            }

            if (drawableTop != null
                    && drawableTop.getBounds().contains(actionX, actionY)) {
                clickListener.onClick(DrawablePosition.TOP);
                return super.onTouchEvent(e);
            }

            if (drawableLeft != null) {
                bounds = null;
                bounds = drawableLeft.getBounds();

                int x, y;
                int extraTapArea = (int) (13 * getResources().getDisplayMetrics().density + 0.5);

                x = actionX;
                y = actionY;
                if (!bounds.contains(actionX, actionY)) {
                    /** Gives the +20 area for tapping. */
                    x = (int) (actionX - extraTapArea);
                    y = (int) (actionY - extraTapArea);

                    if (x <= 0)
                        x = actionX;
                    if (y <= 0)
                        y = actionY;

                    /** Creates square from the smallest value */
                    if (x < y) {
                        y = x;
                    }
                }

                if (bounds.contains(x, y) && clickListener != null) {
                    clickListener
                            .onClick(DrawablePosition.LEFT);
                    e.setAction(MotionEvent.ACTION_CANCEL);
                    return false;
                }
            }

            if (drawableRight != null) {

                bounds = null;
                bounds = drawableRight.getBounds();

                int x, y;
                int extraTapArea = 13;

                /**
                 * IF USER CLICKS JUST OUT SIDE THE RECTANGLE OF THE DRAWABLE
                 * THAN ADD X AND SUBTRACT THE Y WITH SOME VALUE SO THAT AFTER
                 * CALCULATING X AND Y CO-ORDINATE LIES INTO THE DRAWBABLE
                 * BOUND. - this process help to increase the tappable area of
                 * the rectangle.
                 */
                x = (int) (actionX + extraTapArea);
                y = (int) (actionY - extraTapArea);

                /**Since this is right drawable subtract the value of x from the width
                 * of view. so that width - tappedarea will result in x co-ordinate in drawable bound.
                 */
                x = getWidth() - x;

                    /*x can be negative if user taps at x co-ordinate just near the width.
                    * e.g views width = 300 and user taps 290. Then as per previous calculation
                    * 290 + 13 = 303. So subtract X from getWidth() will result in negative value.
                    * So to avoid this add the value previous added when x goes negative.
                    */

                if (x <= 0) {
                    x += extraTapArea;
                }

                    /* If result after calculating for extra tappable area is negative.
                    * assign the original value so that after subtracting
                    * extratapping area value doesn't go into negative value.
                    */

                if (y <= 0)
                    y = actionY;

                /**If drawble bounds contains the x and y points then move ahead.*/
                if (bounds.contains(x, y) && clickListener != null) {
                    clickListener
                            .onClick(DrawablePosition.RIGHT);
                    e.setAction(MotionEvent.ACTION_CANCEL);
                    return false;
                }
                return super.onTouchEvent(e);
            }
        }

        return super.onTouchEvent(e);
    }

    @Override
    protected void finalize() throws Throwable {

        drawableRight = null;
        drawableBottom = null;
        drawableLeft = null;
        drawableTop = null;

        super.finalize();
    }

    public void setDrawableClickListener(DrawableClickListener listener) {
        this.clickListener = listener;
    }

    public void addDrawableAnimationOnTextChange(Context context) {
        addTextChangedListener(new TextAnimationDrawableEditext(this, context));
        setText("");
    }
}
