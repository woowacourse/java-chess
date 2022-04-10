package chess.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonConvertor {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(final Object model) {
        return gson.toJson(model);
    }
}
