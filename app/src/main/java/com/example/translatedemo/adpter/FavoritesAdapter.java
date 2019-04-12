package com.example.translatedemo.adpter;

import android.content.Context;
import android.content.Intent;
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
import com.example.translatedemo.ui.InfomationActivity;
import com.example.translatedemo.model.FavoritesModel;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private ArrayList<FavoritesModel> arrModel;
    private Context context;
//    private OnDataListionler monDataListionler;
    private DataBaseSqliteCreate mDataBaseSqliteCreate;

    public interface setOnClickListener {
        void OnClick(int position);
    }

//    public FavoritesAdapter(OnDataListionler monDataListionler, ArrayList<FavoritesModel> arrModel, Context context) {
//        this.arrModel = arrModel;
//        this.context = context;
////        this.onclick = onclick;
//        this.monDataListionler = monDataListionler;
//        mDataBaseSqliteCreate = new DataBaseSqliteCreate(context);
//    }

    public FavoritesAdapter( ArrayList<FavoritesModel> arrModel, Context context) {
        this.arrModel = arrModel;
        this.context = context;
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
//                    onclick.OnClick(getAdapterPosition());
                    Intent intent= new Intent(context, InfomationActivity.class);
                    intent.putExtra("word",arrModel.get(getAdapterPosition()).getWord());
                    intent.putExtra("denition",arrModel.get(getAdapterPosition()).getDifinition());
                    context.startActivity(intent);
                }
            });
            mimgStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean chek = mDataBaseSqliteCreate.DeleteFavorites(arrModel.get(getAdapterPosition()).getId());
//                    if (chek) {
//                        monDataListionler.arrFavorit(mDataBaseSqliteCreate.selecterFavorites());
//
//                    }
                    arrModel.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
