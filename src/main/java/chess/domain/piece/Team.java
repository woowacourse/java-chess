package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Team {

    WHITE((score, otherScore) -> score >= otherScore),
    BLACK((score, otherScore) -> score <= otherScore);

    private final BiPredicate<Double, Double> winnerCondition;

    Team(BiPredicate<Double, Double> condition) {
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
}
