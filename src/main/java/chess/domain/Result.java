package chess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승리", (myScore, opponentScore) -> myScore > opponentScore),
    DRAW("무승부", (myScore, opponentScore) -> myScore.equals(opponentScore)),
    LOSE("패배", (myScore, opponentScore) -> myScore < opponentScore),
    ;

    private final String name;
    private final BiPredicate<Double, Double> predicate;

    Result(String name, BiPredicate<Double, Double> predicate) {
        this.name = name;
        this.predicate = predicate;
    }


    public static Result decide(final Score my, final Score opponent) {
        return Arrays.stream(Result.values())
                .filter(result -> result.predicate.test(my.getValue(), opponent.getValue()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 구할 수 없습니다."));
    }

    public String getName() {
        return name;
    }
}
