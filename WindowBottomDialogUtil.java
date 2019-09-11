package com.yt.kangaroo.widgets.windowBottomDialog;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 *@content:界面下方对话框实现工具类
 *@time:2019-2-21
 *@build:zhouqiang
 */

public class WindowBottomDialogUtil {
    private static final String TAG = "WindowBottomDialogUtil";
    private PopupWindow mPopupWindow;
//    private LinearLayout mLinearLayout;
    private ConstraintLayout mConstraintLayout;
    private WindowBottomDialog mConfig;
    private WindowBottomDialogAdapter mWindowBottomDialogAdapter;

    protected WindowBottomDialogUtil(WindowBottomDialog config){
        this.mConfig = config;
        if (mConfig.context == null){
            Log.e(TAG, "WindowBottomDialogUtil:context is null");
            onError("context is null");
            return;
        }
        if (mConfig.itemTextList == null){
            onError("itemTextList is null");
            return;

        }
        if (mConfig.itemTextColorEnum == WindowBottomDialog.ItemTextColorEnum.ALONE){
            if (mConfig.itemTextColorList == null){
                onError("itemTextColorList is null");
                return;
            }
            if (mConfig.itemTextColorList.size() != mConfig.itemTextList.size()){
                onError("颜色List 与 内容List 长度不一致");
                return;
            }
        }
        initData();
        initView();
        initPopupWindow();

    }

    private void initData(){
        if (mConfig.showEnum == WindowBottomDialog.ShowEnum.SHARING){ //初始化item尺寸均分下的item宽度与高度
            mConfig.itemWidht = mConfig.dialogWidht;
            mConfig.itemHeigth = mConfig.dialogHeigth / mConfig.itemTextList.size();
        }

    }

    private void initView(){
        mConstraintLayout = new ConstraintLayout(mConfig.context);
        mConstraintLayout.setBackground(WindowBottomDialogBg.dialogBg(mConfig.dialogBgColor
                , mConfig.dialogTopLeftCornerRadius
                , mConfig.dialogTopRightCornerRadius
                , mConfig.dialogBottomLeftCornerRadius
                , mConfig.dialogBottomRightCornerRadius));
        mWindowBottomDialogAdapter = new WindowBottomDialogAdapter(mConfig);
        RecyclerView recyclerView = new RecyclerView(mConfig.context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mConfig.context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mWindowBottomDialogAdapter);
        mConstraintLayout.addView(recyclerView);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.topToTop = layoutParams.PARENT_ID;
        layoutParams.bottomToBottom = layoutParams.PARENT_ID;
        layoutParams.leftToLeft = layoutParams.PARENT_ID;
        layoutParams.rightToRight = layoutParams.PARENT_ID;
        recyclerView.setLayoutParams(layoutParams);

    }

    private void initPopupWindow(){
        mPopupWindow = new PopupWindow(mConstraintLayout, mConfig.dialogWidht, mConfig.dialogHeigth);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                finishDark();
                if (mConfig.listener!=null){
                    mConfig.listener.onDismiss();
                }
            }
        });

    }

    private void onError(String e){
        if (mConfig.listener != null){
            mConfig.listener.onError(e);
        }

    }

    protected void show(){
        mPopupWindow.showAtLocation(((Activity)mConfig.context).getWindow().getDecorView(), Gravity.BOTTOM
                , mConfig.marginX
                , mConfig.marginY);
        startDark();
        if (mConfig.listener != null){
            mConfig.listener.onShow();
        }

    }

    protected void dismiss(){
        mPopupWindow.dismiss();
    }

    /**
     * 将window界面变暗
     */
    private void startDark(){
        WindowManager.LayoutParams params= ((Activity)mConfig.context).getWindow().getAttributes();
        params.alpha=0.7f;
        ((Activity)mConfig.context).getWindow().setAttributes(params);
    }

    /**
     * 恢复window界面亮度
     */
    private void finishDark(){
        WindowManager.LayoutParams params= ((Activity)mConfig.context).getWindow().getAttributes();
        params.alpha=1f;
        ((Activity)mConfig.context).getWindow().setAttributes(params);
    }

    protected void destroy(){
        if (mPopupWindow!=null) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        if (mConfig != null){
            finishDark();
            mConfig.context = null;
            mConfig = null;

        }
    }

}
