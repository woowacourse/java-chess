package chess.util;

import com.google.gson.Gson;
import java.util.Map;

public class BodyParser {
    private final static Gson GSON = new Gson();

    public static Map<String, String> parseToMap(String requestBody) {
        return GSON.fromJson(requestBody, Map.class);
    }
}
