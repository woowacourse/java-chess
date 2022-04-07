package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Team {

    WHITE("white", (score, otherScore) -> score >= otherScore),
    BLACK("black", (score, otherScore) -> score <= otherScore),
    NONE("", (score, otherScore) -> false);

    private final String value;
    private final BiPredicate<Double, Double> winnerCondition;

    Team(String value, BiPredicate<Double, Double> condition) {
        this.value = value;
        this.winnerCondition = condition;
    }

    public static List<Team> findWinner(double white, double black) {
        return Arrays.stream(values())
                .filter(team -> team.winnerCondition.test(white, black))
                .collect(Collectors.toUnmodifiableList());
    }

    public Team change() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    @Override
    public String toString() {
        return value;
    }
}
