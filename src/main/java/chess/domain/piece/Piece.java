package chess.domain.piece;

import java.util.Objects;

import chess.constant.SquareType;
import chess.domain.board.position.Position;

public abstract class Piece {

    final PieceTeam pieceTeam;

    public Piece(PieceTeam pieceTeam) {
        this.pieceTeam = pieceTeam;
    }

    public abstract boolean isMovable(Position source, Position target, SquareType squareType);

    public boolean isMyTeam(Piece other) {
        return this.pieceTeam == other.pieceTeam;
    }

    public boolean isSameColor(PieceTeam otherPieceTeam) {
        return this.pieceTeam == otherPieceTeam;
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
        if (pieceTeam == PieceTeam.WHITE) {
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
        return pieceTeam == piece.pieceTeam;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceTeam);
    }
}
