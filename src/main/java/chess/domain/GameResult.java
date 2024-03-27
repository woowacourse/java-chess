package chess.domain;

import java.util.Objects;
import java.util.function.BiPredicate;

public enum GameResult {
    WHITE_WIN((a, b) -> a > b),
    BLACK_WIN((a, b) -> a < b),
    DRAW(Objects::equals),
    ;

    private final BiPredicate<Double, Double> isResult;

    GameResult(BiPredicate<Double, Double> isResult) {
        this.isResult = isResult;
    }
}
