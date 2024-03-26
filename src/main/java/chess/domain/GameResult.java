package chess.domain;

import java.util.Arrays;
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

    public static GameResult findGameResult(double whiteScore, double blackScore) {
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.isResult.test(whiteScore, blackScore))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("입력에 따른 결과가 존재하지 않습니다."));
    }
}
