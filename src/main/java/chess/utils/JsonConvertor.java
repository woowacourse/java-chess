package chess.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConvertor {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(final Object model){
        return gson.toJson(model);
    }
}
