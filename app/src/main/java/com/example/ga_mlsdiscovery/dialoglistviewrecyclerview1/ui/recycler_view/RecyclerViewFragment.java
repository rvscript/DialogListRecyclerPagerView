package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.ui.recycler_view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
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
    private ImageView mImageView;
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
        mImageView = v.findViewById(R.id.image_view);
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
                .createService(EndPoint.class, getContext());
        Call<List<Photo>> call = endPoint.getPhotosForUser();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                String tempString = "";
                if(response.isSuccessful()) {
                    if (response.raw().cacheResponse()!= null) {
                        Toast.makeText(getContext(), "cache not null", Toast.LENGTH_SHORT).show();
//                        photoList.addAll(response.body());
//                        for (Photo p : photoList) {
//                            Timber.d("Photo info: " + p.getTitle() + "\n");
//                            tempString += "Title: "+p.getTitle()+ "\n"
//                                    +"ID: "+p.getId()+"   AlbumID: "+p.getAlbumId()+ "\n"
//                                    +"URL: "+p.getUrl()+"\n"
//                                    +"ThumbnailURL: "+p.getThumbnailUrl()+"\n";
                    }

                    if(response.raw().networkResponse() != null){
                        Toast.makeText(getContext(), "network response reached", Toast.LENGTH_SHORT).show();

                        photoList.addAll(response.body());
                        for (Photo p : photoList) {
                            Timber.d("Photo info: " + p.getTitle() + "\n");
                            tempString += "Title: " + p.getTitle() + "\n"
                                    + "ID: " + p.getId() + "   AlbumID: " + p.getAlbumId() + "\n"
                                    + "URL: " + p.getUrl() + "\n"
                                    + "ThumbnailURL: " + p.getThumbnailUrl() + "\n";
                        }

                    }
//https://media.giphy.com/media/3oKIPvVDlUdiBMl0dy/giphy.gif
                    //                       Timber.d("Photo info: " + photoList.get(0).getTitle() + "\n");

//                        tempString += "Title: "+photoList.get(0).getTitle()+ "\n"
//                                +"ID: "+photoList.get(0).getId()+"   AlbumID: "+photoList.get(0).getAlbumId()+ "\n"
//                                +"URL: "+photoList.get(0).getUrl()+"\n"
//                                +"ThumbnailURL: "+photoList.get(0).getThumbnailUrl()+"\n";

//                        Glide.with(getActivity())
//                                .asGif()
//                                .listener(new RequestListener<GifDrawable>() {
//                                    @Override
//                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
//                                        return false;
//                                    }
//
//                                    @Override
//                                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
//                                        if(resource instanceof GifDrawable){
//                                            ((GifDrawable)resource).setLoopCount(5);
//                                        }
//                                        return false;
//                                    }
//                                })
//                                .load("https://media.giphy.com/media/3oKIPvVDlUdiBMl0dy/giphy.gif")
//                                .apply(new RequestOptions()
//                                .placeholder(R.drawable.cup_of_coffee)
//                                .centerCrop()
//                                .fitCenter())
//                                .into(mImageView);
                        mTextView.setText(tempString);
                    } else {
                        mImageView.setImageResource(R.drawable.cup_of_coffee);
                        mTextView.setText("Photos N/A");
                    }
                }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(getContext(), "Check Network connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
