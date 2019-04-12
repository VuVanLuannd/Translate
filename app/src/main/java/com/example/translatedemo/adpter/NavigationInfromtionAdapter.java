package com.example.translatedemo.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.translatedemo.model.InForMationModel;
import com.example.translatedemo.R;

import java.util.ArrayList;

public class NavigationInfromtionAdapter extends RecyclerView.Adapter<NavigationInfromtionAdapter.ViewHolder> {
    private ArrayList<InForMationModel> arrName=new ArrayList<>();
    private Context context;
    private setOnClickListener ClickListener;

    public NavigationInfromtionAdapter(ArrayList<InForMationModel> name, Context context) {
    }

    public interface setOnClickListener{
        void onclik(int position);
    }

    public void setOnClickListener(setOnClickListener onClickListener) {
        this.ClickListener = onClickListener;
    }

    public NavigationInfromtionAdapter(ArrayList<InForMationModel> arrName, Context context, setOnClickListener clickListener) {
        this.arrName = arrName;
        this.context = context;
        ClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_navigation_informtion,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtName.setText(arrName.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return arrName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private LinearLayout rlItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtname);
            rlItem=itemView.findViewById(R.id.rlItem);
            rlItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickListener.onclik(getAdapterPosition());
                }
            });
        }
    }
}
