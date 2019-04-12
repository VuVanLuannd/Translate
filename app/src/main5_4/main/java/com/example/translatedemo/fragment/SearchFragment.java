package com.example.translatedemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.translatedemo.R;
import com.example.translatedemo.adpter.SearchViewAdapter;
import com.example.translatedemo.database.DataBaseSqliteCopy;
import com.example.translatedemo.database.DataBaseSqliteCreate;
import com.example.translatedemo.model.SearchModel;
import com.example.translatedemo.database.SqlQuery;
import com.example.translatedemo.utils.Utils;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private View view;
    private SearchViewAdapter adapter;
    private RecyclerView recyclerView;
    private EditText medInput;
    private DataBaseSqliteCreate dataBaseSqliteCreate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initview();
        dataBaseSqliteCreate=new DataBaseSqliteCreate(getContext());
        EditTextInput();
        return view;
    }

    private void EditTextInput() {
        medInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    addRecyclerview1(new SqlQuery(getContext()).Selecter(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s)){
                    ArrayList<SearchModel> arrayList=new ArrayList<>();
                    addRecyclerview1(arrayList);
                }
            }
        });
    }

    private void initview() {
        medInput=view.findViewById(R.id.edInput);
        medInput.setInputType(InputType.TYPE_NULL);
        recyclerView = view.findViewById(R.id.rccSearch);
        medInput.setOnClickListener(this);
    }

    private void addRecyclerview1(final ArrayList<SearchModel> arrName) {
        adapter = new SearchViewAdapter(arrName, getContext(),new SearchViewAdapter.setOnClickListener() {
            @Override
            public void OnClick(int posittion) {
                if(arrName.get(posittion)!=null) {
                    dataBaseSqliteCreate.inSert(arrName.get(posittion));
                }
                Intent intent=new Intent(getContext(), InfomationFragment.class);
                intent.putExtra("denition",arrName.get(posittion).getDifinition());
                intent.putExtra("word",arrName.get(posittion).getWord());

                startActivityForResult(intent,50);
            }
        });
        adapter.setHasStableIds(true);
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edInput:
                medInput.setInputType(InputType.TYPE_CLASS_TEXT);
                Utils.showKeyBoard(medInput,getActivity());
                break;
        }
    }
}
