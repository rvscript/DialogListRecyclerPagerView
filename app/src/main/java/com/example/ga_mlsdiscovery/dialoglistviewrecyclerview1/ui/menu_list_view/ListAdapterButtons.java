package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.ui.menu_list_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.R;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.model.CustomButton;

import java.util.List;
import java.util.Set;

import timber.log.Timber;

//The base adapter needs to receive the list of objects
//it needs to know what to inflate
//and it needs a context
public class ListAdapterButtons extends BaseAdapter {
    private List<CustomButton> customButtons;
    private Context context;
    private LayoutInflater layoutInflater;

    //Set does not hold duplicates
    Set<View> viewSet;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public ListAdapterButtons(List<CustomButton> customButtons, Context context) {
        this.customButtons = customButtons;
        this.context = context;
        viewSet = new ArraySet<View>();
    }

    @Override
    public int getCount() {
        return customButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return customButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ButtonListViewHolder buttonListViewHolder;
        //reuse this view to solve view inflation problem
        if(v == null){
            layoutInflater = layoutInflater.from(this.context);
            v = layoutInflater.inflate(R.layout.custom_button_item, null);
            buttonListViewHolder = new ButtonListViewHolder();
            buttonListViewHolder.mNameText = ((TextView)v.findViewById(R.id.fragment_name_text));
            buttonListViewHolder.mDescText = ((TextView)v.findViewById(R.id.fragment_desc_text));
            //inputs information associated with the view in the view
            //It's basically a way for views to have memories.
            v.setTag(buttonListViewHolder);
        } else {
            //setTag allows us to reuse buttonListViewHolder
            buttonListViewHolder = (ButtonListViewHolder)v.getTag();
        }

        final CustomButton customButton = customButtons.get(position);

        //data is binding everytime the view is scrolled and inflated
        //use viewHolder design pattern
        buttonListViewHolder.mNameText.setText(customButton.getFragmentName());
        buttonListViewHolder.mDescText.setText(customButton.getFragmentDetail());
        viewSet.add(v);
        Timber.i("Index: "+position+" :"+v.hashCode()+ ", size "+viewSet.size());



        return v;
    }

    //ViewHolder design patter solution to stop rebinding of views upon inflation
    /*
    create a ButtonListViewHolder instance in getView() method
     */
    private static class ButtonListViewHolder{
        public TextView mNameText;
        public TextView mDescText;
    }
}
