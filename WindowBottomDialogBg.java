package com.yt.kangaroo.widgets.windowBottomDialog;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class WindowBottomDialogBg {

    protected static GradientDrawable dialogBg(int color
            , float dialogTopLeftCornerRadius
            , float dialogTopRightCornerRadius
            , float dialogBottomLeftCornerRadius
            , float dialogBottomRightCornerRadius){
        GradientDrawable dialogBg = new GradientDrawable();
        dialogBg.setShape(GradientDrawable.RECTANGLE);
        float[] floats = {dialogTopLeftCornerRadius
                , dialogTopLeftCornerRadius
                , dialogTopRightCornerRadius
                , dialogTopRightCornerRadius
                , dialogBottomRightCornerRadius
                , dialogBottomRightCornerRadius
                , dialogBottomLeftCornerRadius
                , dialogBottomLeftCornerRadius};
        dialogBg.setCornerRadii(floats);
        dialogBg.setColor(color);
        return dialogBg;
    }

    protected static StateListDrawable itemBg(int itemBgColor, int itemPressedBgColor, float itemCornerRadius){
        int pressed = android.R.attr.state_pressed;

        GradientDrawable notPressedDrawable = new GradientDrawable();
        notPressedDrawable.setShape(GradientDrawable.RECTANGLE);
        notPressedDrawable.setColor(itemBgColor);
        notPressedDrawable.setCornerRadius(itemCornerRadius);

        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(GradientDrawable.RECTANGLE);
        pressedDrawable.setColor(itemPressedBgColor);
        pressedDrawable.setCornerRadius(itemCornerRadius);

        StateListDrawable itemBg = new StateListDrawable();
        itemBg.addState(new int[]{-pressed}, notPressedDrawable);
        itemBg.addState(new int[]{pressed}, pressedDrawable);

        return itemBg;

    }
}
