package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

public class VuforiaCallBack {

    public String imageName;
    @SerializedName("result_code")
    public String responseCode;
    @SerializedName("target_id")
    public String id;
    public String result;

}
