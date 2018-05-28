package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.ui.menu_list_view;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.R;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.ui.recycler_view.RecyclerViewFragment;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.utility.AppUtility;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private ListView listView;
    private AppUtility appUtility;
    //Android Array Adapter Not usable for objects
    //private ArrayAdapter<String> fragmentNamesAdapter;
    private ListAdapterButtons fragmentButtonsListAdapter;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        listView = v.findViewById(R.id.list_view);
        listView.setClickable(true);
        appUtility = AppUtility.getAppUtility(getActivity().getApplicationContext());
        //create an adapter instance of type String
        //fragmentNamesAdapter= new ArrayAdapter<String>(v.getContext(), R.layout.custom_list_item, appUtility.getBtnFragmentNames());
        fragmentButtonsListAdapter = new ListAdapterButtons(appUtility.getCustomButtonList(), v.getContext());
        //set Adapter in the ListView
        //listView.setAdapter(fragmentNamesAdapter);
        listView.setAdapter(fragmentButtonsListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getContext(), "item: " + (position + 1), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "item: " + (position + 1), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(), "item: " + (position + 1), Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(), "RecyclerView", Toast.LENGTH_SHORT).show();
                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new RecyclerViewFragment())
                                .addToBackStack("recyclerviewfragment")
                                .commit();
                        break;
                    case 4:
                        Toast.makeText(getContext(), "item: " + (position + 1), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getContext(), "No selection made", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

}
