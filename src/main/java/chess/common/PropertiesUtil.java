package chess.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertiesUtil {

    public static Map<String, String> parseAll(final String name) {
        try (final InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(name);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {
            return reader.lines()
                    .collect(Collectors.toMap(
                            it -> it.substring(0, it.indexOf('=')),
                            PropertiesUtil::parseProperty)
                    );
        } catch (Exception e) {
            throw new IllegalArgumentException("초기화오류", e);
        }
    }

    private static String parseProperty(final String it) {
        final String val = it.substring(it.indexOf('=') + 1);
        if (val.startsWith("${")) {
            return System.getenv(val
                    .replace("${", "")
                    .replace("}", ""));
        }
        return val;
    }
}
