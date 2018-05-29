package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.ui.recycler_view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.R;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.api.ApiServiceGenerator;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.api.EndPoint;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.model.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {
    private TextView mTextView;
    private EndPoint endPoint;
    private List<Photo> photoList;
    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mTextView = v.findViewById(R.id.test_text);
        photoList = new ArrayList<>();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeApiCall();
    }

    void makeApiCall() {
        endPoint = ApiServiceGenerator
                .getApiServiceGeneratorInstance()
                .createService(EndPoint.class);
        Call<List<Photo>> call = endPoint.getPhotosForUser();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                String tempString = "";
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        photoList.addAll(response.body());
                        for (Photo p : photoList) {
                            Timber.d("Photo info: " + p.getTitle() + "\n");
                            tempString += "Title: "+p.getTitle()+ "\n"
                                    +"ID: "+p.getId()+"   AlbumID: "+p.getAlbumId()+ "\n"
                                    +"URL: "+p.getUrl()+"\n"
                                    +"ThumbnailURL: "+p.getThumbnailUrl()+"\n";
                        }
                        mTextView.setText(tempString);
                    } else {
                        mTextView.setText("Photos N/A");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(getContext(), "Check Network connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
