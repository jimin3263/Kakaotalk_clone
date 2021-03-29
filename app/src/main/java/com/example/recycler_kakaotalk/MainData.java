package com.example.recycler_kakaotalk;

public class MainData {
   // private int viewType;
    private int iv_profile;
    private String iv_name;
    private String iv_message;

    public MainData(int iv_profile, String iv_name, String iv_message){
        this.iv_profile = iv_profile;
        this.iv_name = iv_name;
        this.iv_message = iv_message;
    //    this.viewType = viewType;
    }
    public int getIv_profile() {
        return iv_profile;
    }

    public void setIv_profile(int iv_profile) {
        this.iv_profile = iv_profile;
    }

    public String getIv_name() {
        return iv_name;
    }

    public void setIv_name(String iv_name) {
        this.iv_name = iv_name;
    }

    public String getIv_message() {
        return iv_message;
    }

    public void setIv_message(String iv_message) {
        this.iv_message = iv_message;
    }

    /*
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

     */
}
