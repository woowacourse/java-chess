package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Team {

    WHITE((score, otherScore) -> score >= otherScore),
    BLACK((score, otherScore) -> score <= otherScore);

    private final BiPredicate<Double, Double> condition;

    Team(BiPredicate<Double, Double> condition) {
        this.condition = condition;
    }

    public static List<Team> findWinner(double white, double black) {
        return Arrays.stream(values())
                .filter(team -> team.condition.test(white, black))
                .collect(Collectors.toUnmodifiableList());
    }

    public String convert(String value) {
        if (this == BLACK) {
            return value.toUpperCase();
        }
        return value.toLowerCase();
    }

    public Team change() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
