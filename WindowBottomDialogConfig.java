package com.yt.kangaroo.widgets.windowBottomDialog;

import android.app.Activity;
import android.view.View;

/**
 *@content:界面下方对话框创建配置与使用类(此对话框只有2个菜单)
 *@time:2019-2-21
 *@build:zhouqiang
 */

public class WindowBottomDialogConfig {
    protected Activity mActivity;
    protected int mHeigth;
    protected int mWidht;
    protected String mTopText;
    protected String mBottomText;
    protected float mTextSize;
    protected int mTopTextColor;
    protected int mBottomTextColor;
    protected int mBgColor;
    protected OnDialogListener mListener;

    public WindowBottomDialogConfig(Activity activity,int widht, int heigth){
        this.mActivity = activity;
        this.mWidht = widht;
        this.mHeigth = heigth;
    }

    /**
     * 设置字体大小 单位是px
     * @param size
     * @return
     */
    public WindowBottomDialogConfig setTextSize(float size){
        this.mTextSize = size;
        return this;
    }

    /**
     * 设置文本
     * @param topText 上方文本
     * @param bottomText 下方文本
     * @return
     */
    public WindowBottomDialogConfig setText(String topText,String bottomText){
        this.mTopText = topText;
        this.mBottomText = bottomText;
        return this;
    }

    /**
     * 设置字体颜色
     * @param topTextColor 上方字体颜色
     * @param bottomTextColor 下方字体颜色
     * @return
     */
    public WindowBottomDialogConfig setTextColor(int topTextColor,int bottomTextColor){
        this.mTopTextColor = topTextColor;
        this.mBottomTextColor = bottomTextColor;
        return this;
    }

    /**
     * 设置背景色
     * @param bgColor
     * @return
     */
    public WindowBottomDialogConfig setBgColor(int bgColor){
        this.mBgColor = bgColor;
        return this;

    }

    /**
     * 设置对话框回调监听
     * @param listener
     * @return
     */
    public WindowBottomDialogConfig setDialogListener(OnDialogListener listener){
        this.mListener = listener;
        return this;

    }

    public Build build(){
        WindowBottomDialogUtil util = new WindowBottomDialogUtil(this);
        return new Build(util);

    }



    public class Build{
        private WindowBottomDialogUtil mUtil;

        public Build(WindowBottomDialogUtil util){
            this.mUtil = util;

        }

        public void show(){
            mUtil.show();
        }

        public void dismiss(){
            mUtil.dismiss();
        }

        public void destroy(){
            mUtil.destroy();
        }

    }

    public interface OnDialogListener{
        void onError(String e);
        void onDismiss();

        /**
         * 上方点击回调
         */
        void onTopClick();

        /**
         * 下方点击回调
         */
        void onBottomClick();

    }

}
