package chess.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonParser {

    private JsonParser() {
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonParser::toJson;
    }
}
