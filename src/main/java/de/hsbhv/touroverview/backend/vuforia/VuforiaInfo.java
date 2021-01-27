package de.hsbhv.touroverview.backend.vuforia;

import de.hsbhv.touroverview.backend.entities.VuforiaCallBack;
import de.hsbhv.touroverview.backend.graphql.MessageUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VuforiaInfo {
    private static VuforiaInfo vuforiaInfo = null;
    private Collection<VuforiaCallBack> arrayList = Collections.synchronizedList(new ArrayList<>());

    public static VuforiaInfo getInstance() {

        if (vuforiaInfo == null) {
            vuforiaInfo = new VuforiaInfo();
        }
        return vuforiaInfo;

    }

    public void updateList(JSONObject json, String name) {
        VuforiaCallBack vuforiaCallBack = MessageUtil.createFromJson(json.toString(), VuforiaCallBack.class);
        vuforiaCallBack.imageName = name;
        if (vuforiaCallBack.responseCode.contains("TargetCreated")) {
            vuforiaCallBack.result = "ERFOLGREICH";
        } else {
            vuforiaCallBack.result = "FEHLER";
        }
        arrayList.add(vuforiaCallBack);
    }

    public void deleteList() {
        arrayList = Collections.synchronizedList(new ArrayList<>());
    }

    public Collection getCollection() {
        return arrayList;
    }
}