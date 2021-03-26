package chess.domain.position;

import chess.domain.board.Paths;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import java.util.Map;

public final class Position {

    private final Coordinate coordinate;
    private final Piece holdingPiece;

    public Position(final Position position, final Piece holdingPiece) {
        this(position.coordinate, holdingPiece);
    }

    public Position(final Coordinate coordinate, final Piece holdingPiece) {
        this.coordinate = coordinate;
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
        return coordinate.isOfColumn(column);
    }

    public Piece holdingPiece() {
        return holdingPiece;
    }
}
