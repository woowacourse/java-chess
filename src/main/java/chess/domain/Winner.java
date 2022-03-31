package chess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Winner {
    BLACKWIN(Team.BLACK, (blackScore, whiteScore) -> blackScore > whiteScore),
    WHITEWIN(Team.WHITE, (blackScore, whiteScore) -> blackScore < whiteScore),
    DRAW(Team.NEUTRALITY, (blackScore, whiteScore) -> blackScore == whiteScore);

    private final Team team;
    private final BiPredicate<Double, Double> predicate;

    Winner(Team team, BiPredicate<Double, Double> predicate) {
        this.team = team;
        this.predicate = predicate;
    }

    public static Winner find(double blackScore, double whiteScore) {
        return Arrays.stream(values())
                .filter(winner -> winner.predicate.test(blackScore, whiteScore))
                .findAny()
                .orElseThrow();
    }

    public Team getTeam() {
        return team;
    }
}
