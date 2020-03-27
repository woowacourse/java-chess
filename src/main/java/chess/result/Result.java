package chess.result;

import chess.score.Score;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN(Score::isHigherThan),
    DRAW(Score::isEqualThan),
    LOSE(Score::isLowerThan);

    private final BiPredicate<Score, Score> resultDeterminer;

    Result(BiPredicate<Score, Score> resultDeterminer) {
        this.resultDeterminer = resultDeterminer;
    }

    public static Result of(Score score, Score counterScore) {
        return Arrays.stream(Result.values())
                .filter(result -> result.resultDeterminer.test(score, counterScore))
                .findAny()
                .get();
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
