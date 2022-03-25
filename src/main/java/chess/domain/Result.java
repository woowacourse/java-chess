package chess.domain;

import java.util.Arrays;

public enum Result {

    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private final int value;

    Result(int value) {
        this.value = value;
    }

    public static Result decide(final Score my, final Score other) {
        final int resultValue = my.decideResultValue(other);

        return Arrays.stream(Result.values())
            .filter(result -> result.value == resultValue)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 구할 수 없습니다."));
    }
}
