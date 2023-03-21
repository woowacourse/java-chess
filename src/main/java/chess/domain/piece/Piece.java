package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public final class Piece {
    private final PieceState pieceState;
    private final Color color;

    public Piece(MoveRule moveRule, Color color) {
        this.pieceState = new PieceState(moveRule);
        this.color = color;
    }

    public List<Position> move(Position currentPosition, Position nextPosition) {
        return pieceState.move(currentPosition, nextPosition);
    }

    public boolean isOpponent(Piece other) {
        return this.color != other.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public String formatName() {
        return pieceState.formatName(color);
    }

    public boolean isPawn() {
        return pieceState.isPawn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(pieceState, piece.pieceState) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceState, color);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceState=" + pieceState +
                ", color=" + color +
                '}';
    }
}
