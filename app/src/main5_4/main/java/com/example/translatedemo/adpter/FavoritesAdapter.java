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
import com.example.translatedemo.model.FavoritesModel;
import com.example.translatedemo.utils.onDataListionler;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private ArrayList<FavoritesModel> arrModel;
    private Context context;
    private setOnClickListener onclick;
    private onDataListionler monDataListionler;
    private DataBaseSqliteCreate mDataBaseSqliteCreate;

    public interface setOnClickListener {
        void OnClick(int position);
    }

    public FavoritesAdapter(onDataListionler monDataListionler, ArrayList<FavoritesModel> arrModel, Context context, setOnClickListener onclick) {
        this.arrModel = arrModel;
        this.context = context;
        this.onclick = onclick;
        this.monDataListionler = monDataListionler;
        mDataBaseSqliteCreate = new DataBaseSqliteCreate(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fragment_search, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txtName.setText(arrModel.get(i).getWord());
        viewHolder.mimgStar.setImageResource(R.drawable.icon_favorites1);
        viewHolder.mimgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean chek = mDataBaseSqliteCreate.DeleteFavorites(arrModel.get(i).getId());
                if (chek) {
                    monDataListionler.arrFavorit(mDataBaseSqliteCreate.selecterFavorites());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private LinearLayout linearLayout;
        private ImageView mimgStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtnameSearch);
            mimgStar = itemView.findViewById(R.id.imgstar);
            linearLayout = itemView.findViewById(R.id.lnItemseach);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.OnClick(getAdapterPosition());
                }
            });

        }
    }
}
