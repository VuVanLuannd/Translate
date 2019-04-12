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
import com.example.translatedemo.model.SearchModel;
import com.example.translatedemo.utils.onDataListionler;

import java.util.ArrayList;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder> {
    private ArrayList<SearchModel> arrSearch = new ArrayList<>();
    private ArrayList<FavoritesModel>arrFavorites;
    private Context mContext;
    private setOnClickListener onclick;
    private DataBaseSqliteCreate mDataBaseSqliteCreate;
    private onDataListionler monDataListionler;
    private boolean check=false;


    public interface setOnClickListener {
        void OnClick(int ppsittion);
    }
    public SearchViewAdapter(ArrayList<SearchModel> arrSearch,onDataListionler monDataListionler, Context context, setOnClickListener onclick) {
        this.arrSearch = arrSearch;
        this.mContext = context;
        this.onclick = onclick;
        mDataBaseSqliteCreate=new DataBaseSqliteCreate(context);
        this.monDataListionler=monDataListionler;
    }

    public SearchViewAdapter(ArrayList<SearchModel> arrSearch, Context context, setOnClickListener onclick) {
        this.arrSearch = arrSearch;
        this.mContext = context;
        this.onclick = onclick;
        mDataBaseSqliteCreate=new DataBaseSqliteCreate(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fragment_search, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(arrSearch.get(i).getWord());
    }

    @Override
    public int getItemCount() {
        return arrSearch.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private LinearLayout linearLayout;
        private ImageView mimgStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtnameSearch);
            mimgStar = itemView.findViewById(R.id.imgstar);
            linearLayout = itemView.findViewById(R.id.lnItemseach);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.OnClick(getAdapterPosition());

                }
            });
            mimgStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchModel favoritesModel=arrSearch.get(getAdapterPosition());
                    if(!check){
                        mimgStar.setImageResource(R.drawable.icon_favorites1);
                        mDataBaseSqliteCreate.insertFavorites(new FavoritesModel(favoritesModel.getWord(),favoritesModel.getDifinition(),favoritesModel.getId()));
                        check=true;
                    } else {
                        mimgStar.setImageResource(R.drawable.icon_favorites);
                        mDataBaseSqliteCreate.DeleteFavorites(favoritesModel.getId());
                        check = false;
                    }
                }
            });
        }

    }
}
