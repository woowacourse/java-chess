package chess.domain;

import java.util.Arrays;
import java.util.function.DoublePredicate;

public enum Result {

    WHITE(weight -> weight > 0),
    DRAW(weight -> weight == 0),
    BLACK(weight -> weight < 0)
    ;

    private final DoublePredicate condition;

    Result(DoublePredicate condition) {
        this.condition = condition;
    }

    public static Result of(double whiteScore, double blackScore) {
        return Arrays.stream(values())
                .filter(result -> result.condition.test(Double.compare(whiteScore, blackScore)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 결과입니다."));
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
