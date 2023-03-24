package domain.piece.move;

import domain.piece.Color;
import domain.piece.Piece;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Situation {

    COLLEAGUE(Piece::hasSameColorWith),
    ENEMY((x, y) -> !(y.hasSameColorWith(Color.NEUTRAL) || x.hasSameColorWith(y))),
    NEUTRAL((x, y) -> y.hasSameColorWith(Color.NEUTRAL))
    ;

    private final BiPredicate<Piece, Piece> selector;

    Situation(final BiPredicate<Piece, Piece> selector) {
        this.selector = selector;
    }

    public static Situation of(final Piece piece, final Piece otherPiece) {
        return Arrays.stream(values())
                .filter(confrontation -> confrontation.selector.test(piece, otherPiece))
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
