package com.yt.kangaroo.widgets.windowBottomDialog;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WindowBottomDialogAdapter extends RecyclerView.Adapter<WindowBottomDialogAdapter.ViewHolder> {
    private WindowBottomDialog mConfig;

    protected WindowBottomDialogAdapter(WindowBottomDialog windowBottomDialog){
        mConfig = windowBottomDialog;

    }

    @NonNull
    @Override
    public WindowBottomDialogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(mConfig.context);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(mConfig.context);
        View line = new View(mConfig.context);
        linearLayout.addView(textView);
        linearLayout.addView(line);
        textView.setBackground(WindowBottomDialogBg.itemBg(mConfig.itemBgColor, mConfig.itemPressedBgColor, mConfig.itemCornerRadius));
        textView.setTextSize(mConfig.imteTextSize);
        LinearLayout.LayoutParams textLayoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        textLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        textLayoutParams.height = mConfig.itemHeigth;
        textLayoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(textLayoutParams);
        textView.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams lineLayoutParams = (LinearLayout.LayoutParams) line.getLayoutParams();
        lineLayoutParams.width = mConfig.partitionLineWidht;
        lineLayoutParams.height = mConfig.partitionLineHeigth;
        lineLayoutParams.gravity = Gravity.CENTER;
        line.setBackgroundColor(mConfig.partitionLineColor);
        line.setLayoutParams(lineLayoutParams);
        final ViewHolder viewHolder = new ViewHolder(linearLayout);
        if (mConfig.listener != null){
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mConfig.listener.onItemClick(viewHolder.getAdapterPosition());
                }
            });
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WindowBottomDialogAdapter.ViewHolder holder, int position) {
        String itemContent = mConfig.itemTextList.get(position);
        holder.itemText.setText(itemContent);
        if (mConfig.itemTextColorEnum == WindowBottomDialog.ItemTextColorEnum.UNIFY) {
            holder.itemText.setTextColor(mConfig.allItemTextColor);
        }else if (mConfig.itemTextColorEnum == WindowBottomDialog.ItemTextColorEnum.ALONE){
            holder.itemText.setTextColor(mConfig.itemTextColorList.get(position));
        }


    }

    @Override
    public int getItemCount() {
        return mConfig.itemTextList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = (TextView)((LinearLayout)itemView).getChildAt(0);
        }
    }
}
