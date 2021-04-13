package chess.dao;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Serializer {
    private static Serializer instance;

    private final Gson gson;

    private Serializer(Gson gson) {
        this.gson = gson;
    }

    public static Serializer getInstance() {
        if (instance == null) {
            instance = new Serializer(new Gson());
        }
        return instance;
    }

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public String toJson(Object src) {
        return gson.toJson(src);
    }
}
