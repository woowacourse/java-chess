package chess.domain.score;

import java.util.function.Function;

public class PawnScoring implements Function<Boolean, Double> {
    @Override
    public Double apply(Boolean aBoolean) {
        if (aBoolean) {
            return 0.5;
        }
        return 1.0;
    }
}
