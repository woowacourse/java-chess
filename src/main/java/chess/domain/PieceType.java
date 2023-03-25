package chess.domain;

import chess.domain.point.Point;

import java.util.List;

public enum PieceType {
    PAWN(Point.create(1)),
    ROOK(Point.create(5)),
    KNIGHT(Point.create(2.5)),
    BISHOP(Point.create(3)),
    QUEEN(Point.create(9)),
    KING(Point.create(0)),
    EMPTY(Point.create(0));

    private final Point point;

    PieceType(final Point point) {
        this.point = point;
    }

    public static Point sum(final List<PieceType> pieceTypes) {
        return pieceTypes.stream()
                .map(pieceType -> pieceType.point)
                .reduce(Point::plus)
                .orElse(Point.ZERO);
    }
}
