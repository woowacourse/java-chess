package chess.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class JsonUtil {

    public static String serialize(Map<String, String> jsonMap) {
        String json = "{";
        json += jsonMap.entrySet()
                .stream()
                .map(JsonUtil::makeKeyValueString)
                .collect(Collectors.joining(","));
        json += "}";
        return json;
    }

    private static String makeKeyValueString(Entry<String, String> data) {
        String keyValueString = "";
        keyValueString += "\"" + data.getKey() + "\"";
        keyValueString += ":";
        keyValueString += "\"" + data.getValue() + "\"";
        return keyValueString;
    }
}
