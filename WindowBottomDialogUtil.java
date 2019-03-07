package com.yt.kangaroo.widgets.windowBottomDialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yt.kangaroo.R;
import com.yt.kangaroo.utils.L;
/**
 *@content:界面下方对话框实现工具类
 *@time:2019-2-21
 *@build:zhouqiang
 */

public class WindowBottomDialogUtil {
    private static final String TAG = "WindowBottomDialogUtil";
    private Context mContext;
    private PopupWindow mPopupWindow;
    private LinearLayout mLinearLayout;
    private WindowBottomDialogConfig mConfig;

    protected WindowBottomDialogUtil(WindowBottomDialogConfig config){
        this.mConfig = config;
        this.mContext = mConfig.mActivity.getApplicationContext();
        if (mConfig.mActivity == null){
            Log.e(TAG, "WindowBottomDialogUtil:你传入的 Activity 为空");
            mConfig.mListener.onError("你传入的 Activity 为空");
            return;
        }
        initLayout();
        initPopupWindow();

    }

    private void initLayout(){
        mLinearLayout = new LinearLayout(mContext);
        L.ee(TAG,"mLinearLayout.getWidth="+mLinearLayout.getWidth()+"mLinearLayout.getHeight()="+mLinearLayout.getHeight());
        mLinearLayout.setBackgroundColor(0xFFFFFF);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView top = new TextView(mContext);
        TextView bottom = new TextView(mContext);
        mLinearLayout.addView(top);
        mLinearLayout.addView(bottom);

        LinearLayout.LayoutParams topParams = new LinearLayout.LayoutParams(top.getLayoutParams());
        topParams.weight = mConfig.mWidht;
        topParams.height = mConfig.mHeigth/2;
        top.setLayoutParams(topParams);
        LinearLayout.LayoutParams bottomParams = new LinearLayout.LayoutParams(top.getLayoutParams());
        bottomParams.weight = mConfig.mWidht;
        bottomParams.height = mConfig.mHeigth/2;
        bottom.setLayoutParams(bottomParams);

        if (TextUtils.isEmpty(mConfig.mTopText)){
            top.setText("top");
        }else {
            top.setText(mConfig.mTopText);

        }
        if (TextUtils.isEmpty(mConfig.mBottomText)){
            bottom.setText("Bottom");
        }else {
            bottom.setText(mConfig.mBottomText);

        }
        if (mConfig.mTextSize == 0){
            top.setTextSize(17);
            bottom.setTextSize(17);
        }else {
            top.setTextSize(mConfig.mTextSize);
            bottom.setTextSize(mConfig.mTextSize);
        }
        if (mConfig.mTopTextColor == 0){
            top.setTextColor(0xFF000000);
        }else {
            top.setTextColor(mConfig.mTopTextColor);

        }
        if (mConfig.mBottomTextColor == 0){
            bottom.setTextColor(0xFF000000);
        }else {
            bottom.setTextColor(mConfig.mBottomTextColor);

        }
        if (mConfig.mBgColor == 0){
            top.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
            bottom.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));

        }else {
            top.setBackgroundColor(mConfig.mBgColor);
            bottom.setBackgroundColor(mConfig.mBgColor);
        }

        top.setGravity(Gravity.CENTER);
        bottom.setGravity(Gravity.CENTER);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfig.mListener!=null){
                    mConfig.mListener.onTopClick();
                }

            }
        });



        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfig.mListener!=null){
                    mConfig.mListener.onBottomClick();
                }

            }
        });

    }

    private void initPopupWindow(){
        mPopupWindow = new PopupWindow(mLinearLayout,mConfig.mWidht,mConfig.mHeigth);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                finishDark();
                if (mConfig.mListener!=null){
                    mConfig.mListener.onDismiss();
                }
            }
        });

    }

    protected void show(){
        mPopupWindow.showAtLocation(mConfig.mActivity.getWindow().getDecorView(),Gravity.BOTTOM,0,0);
        startDark();

    }

    protected void dismiss(){
        mPopupWindow.dismiss();
    }

    /**
     * 将window界面变暗
     */
    private void startDark(){
        WindowManager.LayoutParams params= mConfig.mActivity.getWindow().getAttributes();
        params.alpha=0.7f;
        mConfig.mActivity.getWindow().setAttributes(params);
    }

    /**
     * 恢复window界面亮度
     */
    private void finishDark(){
        WindowManager.LayoutParams params= mConfig.mActivity.getWindow().getAttributes();
        params.alpha=1f;
        mConfig.mActivity.getWindow().setAttributes(params);
    }

    protected void destroy(){
        if (mPopupWindow!=null) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        if (mConfig != null){
            finishDark();
            mConfig.mActivity = null;
            mConfig = null;

        }
        mContext = null;
        mLinearLayout = null;
    }

}
