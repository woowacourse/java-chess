package chess.controller.web.utils;

import com.google.gson.Gson;

public class JsonConverter {

    private static final Gson GSON = new Gson();

    public static <T> T fromJson(final String json, Class<T> classType) {
        return GSON.fromJson(json, classType);
    }

    public static String toJson(final Object object) {
        return GSON.toJson(object);
    }
}



