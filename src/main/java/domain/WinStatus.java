package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum WinStatus {
    WHITE_WIN((whiteScore, blackScore) -> whiteScore.compareTo(blackScore) > 0),
    BLACK_WIN((whiteScore, blackScore) -> blackScore.compareTo(whiteScore) > 0),
    DRAW(Score::equals);

    private final BiPredicate<Score, Score> condition;

    WinStatus(final BiPredicate<Score, Score> condition) {
        this.condition = condition;
    }

    public static WinStatus of(final Score whiteScore, final Score blackScore) {
        return Arrays.stream(values())
                .filter(status -> status.condition.test(whiteScore, blackScore))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("결과를 구할 수 없습니다."));
    }
}
