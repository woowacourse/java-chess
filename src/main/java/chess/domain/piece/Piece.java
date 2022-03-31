package chess.domain.piece;

import java.util.Objects;

import chess.constant.MoveType;
import chess.domain.board.position.Position;

public abstract class Piece {

    final PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public abstract boolean isMovable(Position source, Position target, MoveType moveType);

    public boolean isMyTeam(Piece other) {
        return this.pieceColor == other.pieceColor;
    }

    public boolean isSameColor(PieceColor otherPieceColor) {
        return this.pieceColor == otherPieceColor;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isKnight() {
        return this instanceof Knight;
    }

    public abstract double getScore();

    public abstract String getConcreteEmblem();

    public String getEmblem() {
        if (pieceColor == PieceColor.WHITE) {
            return getConcreteEmblem().toLowerCase();
        }
        return getConcreteEmblem();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Piece piece = (Piece)o;
        return pieceColor == piece.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor);
    }
}
