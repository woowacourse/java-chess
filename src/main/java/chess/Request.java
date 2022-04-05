package chess;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

public final class Request {

    private final Map<String, String> value;

    private Request(final Map<String, String> value) {
        this.value = value;
    }

    public static Request of(final String body) {
        return Arrays.stream(body.strip().split("&"))
                .map(s -> s.split("="))
                .collect(collectingAndThen(toMap(Request::extractKey, Request::extractValue), Request::new));
    }

    public static String extractKey(final String[] value) {
        return value[0];
    }

    public static String extractValue(final String[] value) {
        return value[1];
    }

    public String command() {
        return value.get("command");
    }
}
