# windowBottomDialog
界面下方弹出的对话框


<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu" /></a>
珍惜生命,拒绝996

	/**
     * 使用方法参考
     *
     * @return
     */
    private WindowBottomDialog.Build moreDialog() {
        if (mMoreDialog == null) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int deviceWidth = displayMetrics.widthPixels;
            int deviceHeigh = displayMetrics.heightPixels;
            int dialogHeigh = (int) (deviceHeigh * 0.33);

            List<String> itemList = new ArrayList<>();
            itemList.add("全部");
            itemList.add("普通");
            itemList.add("活动");
            itemList.add("取消");

            mMoreDialog = new WindowBottomDialog(mTHomeActivity, deviceWidth, dialogHeigh)
                    .setDialogCorner(15, 15, 0, 0)
                    .setTextColor(getResources().getColor(R.color.fontColorMain1))
                    .setItemBgColor(Color.TRANSPARENT, Color.TRANSPARENT)
                    .setItemTextSize(17)
                    .setItemTextList(itemList)
                    .setPartitionLineColor(getResources().getColor(R.color.colorPartitionLine))
                    .setPartitionLineWidht(deviceWidth, 1)
                    .setDialogListener(new WindowBottomDialog.OnDialogListener() {
                        @Override
                        public void onShow() {

                        }

                        @Override
                        public void onDismiss() {

                        }

                        @Override
                        public void onItemClick(int position) {
                            switch (position) {
                                case 0:
                                    //全部
                                    break;

                                case 1:
                                    //普通
                                    break;

                                case 2:
                                    //活动
                                    break;

                                case 3:
                                    mMoreDialog.dismiss();
                                    break;

                                default:
                                    break;
                            }

                        }

                        @Override
                        public void onError(String e) {

                        }
                    })
                    .build();

        }
        return mMoreDialog;

    }
