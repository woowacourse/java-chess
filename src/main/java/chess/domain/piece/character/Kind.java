package chess.domain.piece.character;

import java.util.stream.Stream;

public enum Kind {
    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0),
    ;

    private final double point;

    Kind(double point) {
        this.point = point;
    }

    public static double sumPoint(Stream<Kind> kinds) {
        return kinds.mapToDouble(kind -> kind.point)
                .sum();
    }
}
