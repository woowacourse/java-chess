package chess.service.util;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    private static final Gson gson = new Gson();

    private ResponseUtil() {
    }

    public static String toErrorResponse(final String errorMessage) {
        final Map<String, String> map = new HashMap<>();
        map.put("error", errorMessage);
        return gson.toJson(map);
    }
}
