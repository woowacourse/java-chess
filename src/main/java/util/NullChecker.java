package util;

import java.util.Arrays;
import java.util.Objects;

public class NullChecker {

    private NullChecker() {}

    public static void validateNotNull(Object... objects) {
        if(Arrays.stream(objects).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("인자로 Null을 사용하실 수 없습니다.");
        }
    }
}
