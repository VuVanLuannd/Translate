package com.example.translatedemo.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.translatedemo.R;
import com.example.translatedemo.adpter.SearchViewAdapter;
import com.example.translatedemo.database.DataBaseSqliteCreate;
import com.example.translatedemo.database.MySqlQuery;
import com.example.translatedemo.model.SearchModel;
import com.example.translatedemo.ui.InfomationActivity;
import com.example.translatedemo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private View view;
    private SearchViewAdapter adapter;
    private RecyclerView recyclerView;
    private EditText medInput;
    private ImageView imgVoiec;
    private DataBaseSqliteCreate dataBaseSqliteCreate;
    private final int keyRequestCode = 50;
    private List<SearchModel>  arrNameOn;
    private MySqlQuery sqlQuery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        initview();
        dataBaseSqliteCreate = new DataBaseSqliteCreate(getContext());
        sqlQuery = new MySqlQuery(getContext());
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
                arrNameOn = sqlQuery.Selecter(medInput.getText().toString());
                addReCycLerviewSearch(arrNameOn);
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(s)) {
                    arrNameOn.clear();

                }else {
                    arrNameOn = sqlQuery.Selecter(medInput.getText().toString().trim());
                    addReCycLerviewSearch(arrNameOn);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initview() {
        medInput = view.findViewById(R.id.edInput);
        medInput.setInputType(InputType.TYPE_NULL);

        recyclerView = view.findViewById(R.id.rccSearch);
        medInput.setOnClickListener(this);
        imgVoiec = view.findViewById(R.id.micSearch);
        imgVoiec.setOnClickListener(this);
    }

    private void addReCycLerviewSearch(final List<SearchModel> arrName) {
        adapter = new SearchViewAdapter(arrName, getContext(), new SearchViewAdapter.setOnClickListener() {
            @Override
            public void OnClick(int posittion) {
                if (arrName.get(posittion) != null) {
                    dataBaseSqliteCreate.inSert(arrName.get(posittion));
                }
                Intent intent = new Intent(getContext(), InfomationActivity.class);
                intent.putExtra("denition", arrName.get(posittion).getDifinition());
                intent.putExtra("word", arrName.get(posittion).getWord());

                startActivityForResult(intent, 50);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }

    public void getSpeech(View view) {
        String temp = "us";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, temp);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, temp);
        intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, temp);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, temp);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, temp);
        intent.putExtra(RecognizerIntent.EXTRA_RESULTS, temp);

        try {
            startActivityForResult(intent, keyRequestCode);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getContext(), "error",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case keyRequestCode:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    medInput.setText(" " + result.get(0));
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edInput:
                medInput.setInputType(InputType.TYPE_CLASS_TEXT);
                Utils.showKeyBoard(medInput, getActivity());
                break;
            case R.id.micSearch:
                getSpeech(imgVoiec);
                break;
        }
    }
}
