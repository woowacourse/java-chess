package chess.domain.game;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Outcome {
    WIN("승", (score, scoreToCompare) -> score > scoreToCompare),
    DRAW("무", Double::equals),
    LOSE("패", (score, scoreToCompare) -> score < scoreToCompare);

    private final String outcome;
    private final BiPredicate<Double, Double> expression;

    Outcome(String outcome, BiPredicate<Double, Double> expression) {
        this.outcome = outcome;
        this.expression = expression;
    }

    public static String outcome(double score, double scoreToCompare) {
        return Arrays.stream(values())
                .filter(outcome -> outcome.expression.test(score, scoreToCompare))
                .findFirst()
                .map(Outcome::getOutcome)
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getOutcome() {
        return outcome;
    }
}
