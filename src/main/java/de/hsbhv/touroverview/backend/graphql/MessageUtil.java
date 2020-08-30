package de.hsbhv.touroverview.backend.graphql;

import com.google.gson.Gson;

public class MessageUtil {
    public static <T> T createFromJson(byte[] json, Class msgClass) {
        return createFromJson(new String(json), msgClass);
    }

    public static <T> T createFromJson(String json, Class msgClass) {
        return (T) (new Gson().fromJson(json.trim(), msgClass));
    }
}
