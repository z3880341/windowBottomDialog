package com.yt.kangaroo.widgets.windowBottomDialog;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.ColorInt;

import java.util.List;

/**
 *@content:界面下方对话框创建配置与使用类(此对话框只有2个菜单)
 *@time:2019-2-21
 *@build:zhouqiang
 */

public class WindowBottomDialog {
    protected Context context;
    protected int dialogWidht;                              //Dialog宽度
    protected int dialogHeigth;                             //dialog高度
    protected int marginX = 0;                              //上下外部边距 可以负数
    protected int marginY = 0;                              //左右外部边距
    protected int dialogBgColor = Color.WHITE;              //Dialog背景颜色
    protected float dialogTopLeftCornerRadius = 0;          //Dialog左上圆角半径
    protected float dialogTopRightCornerRadius = 0;         //Dialog右上圆角半径
    protected float dialogBottomLeftCornerRadius = 0;       //Dialog左下圆角半径
    protected float dialogBottomRightCornerRadius = 0;      //Dialog右下圆角半径
    protected List<String> itemTextList;                    //item文本内容List
    protected float imteTextSize = 17;                      //item文字尺寸
    protected int allItemTextColor = Color.BLACK;           //item全部文字颜色
    protected List<Integer> itemTextColorList;              //item单个颜色List
    protected int itemWidht = 0;                            //item宽
    protected int itemHeigth = 0;                           //item高
    protected float itemCornerRadius = 0;                   //item的圆角半径
    protected int itemBgColor = Color.WHITE;                //item背景颜色
    protected int itemPressedBgColor = Color.WHITE;         //item按下背景颜色
    protected int partitionLineColor = Color.TRANSPARENT;   //分割线颜色
    protected int partitionLineWidht = 0;                   //分割线宽度
    protected int partitionLineHeigth = 0;                  //分割线高度
    protected OnDialogListener listener;                    //状态与点击回调
    protected ShowEnum showEnum = ShowEnum.SHARING;         //显示模式 默认为均分模式
    protected ItemTextColorEnum itemTextColorEnum = ItemTextColorEnum.UNIFY;    //item文字配色模式

    public WindowBottomDialog(Context context, int dialogWidht, int dialogHeigth){
        this.context = context;
        this.dialogWidht = dialogWidht;
        this.dialogHeigth = dialogHeigth;
    }

    public WindowBottomDialog setMarginX(int marginX) {
        this.marginX = marginX;
        return this;
    }

    public WindowBottomDialog setMarginY(int marginY) {
        this.marginY = marginY;
        return this;
    }

    public WindowBottomDialog setDialogBgColor(int dialogBgColor) {
        this.dialogBgColor = dialogBgColor;
        return this;
    }

    /**
     * 设置对话框四个角的圆角
     * @param dialogTopLeftCornerRadius
     * @param dialogTopRightCornerRadius
     * @param dialogBottomLeftCornerRadius
     * @param dialogBottomRightCornerRadius
     * @return
     */
    public WindowBottomDialog setDialogCorner(float dialogTopLeftCornerRadius
            , float dialogTopRightCornerRadius
            , float dialogBottomLeftCornerRadius
            , float dialogBottomRightCornerRadius){
        this.dialogTopLeftCornerRadius = dip2px(context, dialogTopLeftCornerRadius);
        this.dialogTopRightCornerRadius = dip2px(context, dialogTopRightCornerRadius);
        this.dialogBottomLeftCornerRadius = dip2px(context, dialogBottomLeftCornerRadius);
        this.dialogBottomRightCornerRadius = dip2px(context, dialogBottomRightCornerRadius);
        return this;
    }


    /**
     * 设置字体大小 单位是px
     * @param imteTextSize
     * @return
     */
    public WindowBottomDialog setItemTextSize(float imteTextSize){
        this.imteTextSize = imteTextSize;
        return this;
    }

    /**
     * 设置item内容集合
     * @return
     */
    public WindowBottomDialog setItemTextList(List<String> itemTextList){
        this.itemTextList = itemTextList;
        return this;
    }

    /**
     * 设置全部item字体颜色
     * @param allItemTextColor
     * @return
     */
    public WindowBottomDialog setTextColor(@ColorInt int allItemTextColor){
        this.allItemTextColor = allItemTextColor;
        itemTextColorEnum = ItemTextColorEnum.UNIFY;
        return this;
    }

    public WindowBottomDialog setItemTextColorList(List<Integer> itemTextColorList) {
        this.itemTextColorList = itemTextColorList;
        itemTextColorEnum = ItemTextColorEnum.ALONE;
        return this;
    }

    public WindowBottomDialog setItemSize(int itemWidht, int itemHeigth) {
        this.itemWidht = itemWidht;
        this.itemHeigth = itemHeigth;
        this.showEnum = ShowEnum.SCROLL;
        return this;
    }

    public WindowBottomDialog setItemCornerRadius(float itemCornerRadius) {
        this.itemCornerRadius = itemCornerRadius;
        return this;
    }

    /**
     * 设置item背景色
     * @param itemBgColor
     * @return
     */
    public WindowBottomDialog setItemBgColor(@ColorInt int itemBgColor, @ColorInt int itemPressedBgColor){
        this.itemBgColor = itemBgColor;
        this.itemPressedBgColor = itemPressedBgColor;
        return this;

    }

    /**
     * 设置对话框回调监听
     * @param listener
     * @return
     */
    public WindowBottomDialog setDialogListener(OnDialogListener listener){
        this.listener = listener;
        return this;
    }

    public WindowBottomDialog setPartitionLineColor(@ColorInt int partitionLineColor) {
        this.partitionLineColor = partitionLineColor;
        return this;
    }

    public WindowBottomDialog setPartitionLineWidht(int partitionLineWidht, int partitionLineHeigth) {
        this.partitionLineWidht = partitionLineWidht;
        this.partitionLineHeigth = partitionLineHeigth;
        return this;
    }

    public Build build(){
        return new Build(new WindowBottomDialogUtil(this));

    }

    /**
     * 根据手机分辨率从DP转成PX
     * @param context
     * @param dpValue
     * @return
     */
    private static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
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
        void onShow();
        void onDismiss();
        void onItemClick(int position);
        void onError(String e);

    }

    protected enum ShowEnum{
        SHARING //item尺寸均分整个dialog的尺寸
        ,SCROLL; //支持设置单个item尺寸,并且dialog将会滚动item
    }
    protected enum ItemTextColorEnum{
        UNIFY   //全部item文字颜色统一
        ,ALONE; //item文字单独配色
    }

}
