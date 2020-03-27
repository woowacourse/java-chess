package chess.util;

import java.util.Arrays;
import java.util.Objects;

public class NullValidator {
    private NullValidator() { /* prevent creating instance */ }

    public static void validateNull(Object... objects) {
        boolean isAnyNull = Arrays.stream(objects)
                .anyMatch(Objects::isNull);
        if (isAnyNull) {
            throw new IllegalArgumentException("null이 들어왔습니다.");
        }
    }
}
