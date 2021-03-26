package chess.domain.position;

import chess.domain.board.Paths;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.strategy.Direction;
import java.util.Map;

public final class Position {

    private final Notation notation;
    private final Piece holdingPiece;

    public Position(final Position position, final Piece holdingPiece) {
        this(position.notation, holdingPiece);
    }

    public Position(final Notation notation, final Piece holdingPiece) {
        this.notation = notation;
        this.holdingPiece = holdingPiece;
    }

    public Position replacePiece(Piece piece) {
        return new Position(this, piece);
    }

    public Position copyPieceFrom(Position source) {
        return new Position(this, source.holdingPiece);
    }

    public boolean holdingPieceIsColor(PieceColor color) {
        return holdingPiece.isColor(color);
    }

    public Paths availablePaths(Map<Notation, Position> boardPositions) {
        return new Paths(holdingPiece, notation, boardPositions);
    }

    public boolean isEmpty() {
        return holdingPiece.isEmpty();
    }

    public boolean containsPawn() {
        return holdingPiece.isPawn();
    }

    public boolean containsKing() {
        return holdingPiece.isKing();
    }

    public boolean isOfColumn(Column column) {
        return notation.isOfColumn(column);
    }

    public Piece holdingPiece() {
        return holdingPiece;
    }

    public Notation notation() {
        return notation;
    }
}
