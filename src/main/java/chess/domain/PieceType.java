package chess.domain;

import chess.domain.piece.*;
import chess.domain.point.Point;

import java.util.List;
import java.util.function.Function;

public enum PieceType {
    PAWN(Point.create(1), Pawn::from),
    ROOK(Point.create(5), Rook::from),
    KNIGHT(Point.create(2.5), Knight::from),
    BISHOP(Point.create(3), Bishop::from),
    QUEEN(Point.create(9), Queen::from),
    KING(Point.create(0), King::from),
    EMPTY(Point.create(0), color -> Empty.create());

    private final Point point;
    private final Function<Color, Piece> createPiece;

    PieceType(final Point point, final Function<Color, Piece> createPiece) {
        this.point = point;
        this.createPiece = createPiece;
    }

    public static Point sum(final List<PieceType> pieceTypes) {
        return pieceTypes.stream()
                .map(pieceType -> pieceType.point)
                .reduce(Point::plus)
                .orElse(Point.ZERO);
    }

    public Piece newInstance(final Color color) {
        return createPiece.apply(color);
    }
}
