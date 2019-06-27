package chess.utils;

import com.google.gson.Gson;

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static String toJson(Object object){
        return gson.toJson(object);
    }
}
