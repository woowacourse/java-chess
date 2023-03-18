package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Set;

public final class Square {

    private Piece piece;

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece.isEmpty();
    }

    public Set<Position> computePath(Position source, Position target) {
        return piece.computePathWithValidate(source, target);
    }

    public boolean canMovePiece(Map<Position, Boolean> isEmptyPath, Position source, Position target) {
        return piece.canMove(isEmptyPath, source, target);
    }

    public void changePiece(Square other) {
        this.piece = other.piece;
    }

    public void makeEmpty() {
        this.piece = new Empty();
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean equalsColor(final Square targetSquare) {
        return piece.equalsColor(targetSquare.piece);
    }

    public boolean equalsColor(final Color color) {
        return piece.equalsColor(color);
    }
}

