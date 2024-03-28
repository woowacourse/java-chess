package chess.domain.score;

import java.util.function.Function;

public enum ScoreStatus {
    DEFAULT(score -> score),
    HALF(Score::half),
    ;

    private final Function<Score, Score> function;

    ScoreStatus(final Function<Score, Score> function) {
        this.function = function;
    }

    public Score calculate(final Score score) {
        return function.apply(score);
    }
}
