package com.example.translatedemo.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.translatedemo.R;
import com.example.translatedemo.database.DataBaseSqliteCreate;
import com.example.translatedemo.model.HistoryModel;
import com.example.translatedemo.utils.IUpdateListView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHoder> {
    private ArrayList<HistoryModel> arrayList;
    private Context context;
    private IUpdateListView iUpdateListView;
    private setOnClickListener ClickListener;
    private DataBaseSqliteCreate databaseSqlite;
    public interface setOnClickListener{
        void onclik(int position);
    }

    public void setOnClickListener(setOnClickListener onClickListener) {
        this.ClickListener = onClickListener;
    }
    public HistoryAdapter(ArrayList<HistoryModel> arrayList, Context context, IUpdateListView iUpdateListView,setOnClickListener onClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        databaseSqlite = new DataBaseSqliteCreate(context);
        this.iUpdateListView = iUpdateListView;
        this.ClickListener=onClickListener;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fragment_history, viewGroup, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, final int i) {
        viewHoder.txtName.setText(arrayList.get(i).getWord());
        final HistoryModel obeject = arrayList.get(i);

        viewHoder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean kt= databaseSqlite.Deleted(obeject.getId());
               if(kt)
               {
                   iUpdateListView.update(databaseSqlite.selecterHistory());
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView imageView;
        private LinearLayout linearLayout;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtDistplayHistory);
            imageView = itemView.findViewById(R.id.imgDeleteHistory);
            linearLayout=itemView.findViewById(R.id.lnDelete);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickListener.onclik(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
