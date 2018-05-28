package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.utility;

import android.content.Context;

import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.R;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.model.CustomButton;

import java.util.ArrayList;
import java.util.List;

public class AppUtility {
    private String [] btnFragmentNames;
    private String [] btnFragmentDesc;

    private Context context;

    private List<CustomButton> customButtonList;

    private static AppUtility appUtility;

    private AppUtility(Context context){
        this.context = context;
        btnFragmentNames = context.getResources().getStringArray(R.array.fragment_selector);
        btnFragmentDesc = context.getResources().getStringArray(R.array.fragment_detail);

        customButtonList= new ArrayList<CustomButton>();
        for(int i=0; i<btnFragmentNames.length; i++){
            customButtonList.add(new CustomButton(btnFragmentNames[i], btnFragmentDesc[i]));
        }
    }

    public static AppUtility getAppUtility(Context context){
        if(appUtility == null){
            appUtility= new AppUtility(context);
        }
        return appUtility;
    }

    public String[] getBtnFragmentNames() {
        return btnFragmentNames;
    }

    public String[] getBtnFragmentDesc() {
        return btnFragmentDesc;
    }

    public List<CustomButton>getCustomButtonList(){
        if(customButtonList.size() > 0){
            return customButtonList;
        } else {
            customButtonList.add(new CustomButton("No Available Fragments","No Available Details"));
            return customButtonList;

        }
    }
}
