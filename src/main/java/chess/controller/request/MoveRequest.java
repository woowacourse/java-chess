package chess.controller.request;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

public class MoveRequest {
    private final Map<String, String> value;

    public MoveRequest(Map<String, String> value) {
        this.value = value;
    }

    public static MoveRequest of(String body) {
        return Arrays.stream(body.strip().split("\r\n"))
                .map(s -> s.split("="))
                .collect(collectingAndThen(toMap(MoveRequest::extractKey, MoveRequest::extractValue), MoveRequest::new));
    }

    public static String extractKey(final String[] value) {
        return value[0];
    }

    public static String extractValue(final String[] value) {
        return value[1];
    }

    public String get(String key) {
        return value.get(key);
    }
}
