package domain.piece.move;

import domain.square.Color;
import domain.square.Square;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Situation {

    ENEMY((x, y) -> y.getColor() != Color.NEUTRAL && x.getColor() != y.getColor()),
    COLLEAGUE((x, y) -> x.getColor() == y.getColor()),
    NEUTRAL((x, y) -> y.getColor() == Color.NEUTRAL)
    ;

    private final BiPredicate<Square, Square> selector;

    Situation(final BiPredicate<Square, Square> selector) {
        this.selector = selector;
    }

    public static Situation of(final Square square, final Square otherSquare) {
        return Arrays.stream(values())
                .filter(confrontation -> confrontation.selector.test(square, otherSquare))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 적팀인지 동료인지를 판단할 수 없습니다."));
    }

    public boolean meetColleague() {
        return this == COLLEAGUE;
    }

    public boolean meetNeutral() {
        return this == NEUTRAL;
    }
}
