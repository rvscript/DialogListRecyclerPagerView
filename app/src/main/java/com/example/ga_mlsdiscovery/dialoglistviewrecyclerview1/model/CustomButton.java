package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.model;

public class CustomButton {
    private String fragmentName;
    private String fragmentDetail;

    public CustomButton(String fragmentName, String fragmentDetail) {
        this.fragmentName = fragmentName;
        this.fragmentDetail = fragmentDetail;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public String getFragmentDetail() {
        return fragmentDetail;
    }

    public void setFragmentDetail(String fragmentDetail) {
        this.fragmentDetail = fragmentDetail;
    }
}
