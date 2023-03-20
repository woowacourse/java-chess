package chess.domain.piece.type;

import chess.domain.chess.CampType;
import chess.domain.piece.Position;
import chess.domain.piece.move.piece.MoveRule;

import java.util.Objects;

public class Piece {
    private final PieceType pieceType;
    private final CampType campType;
    protected final MoveRule moveRule;

    public Piece(final PieceType pieceType, final CampType campType, final MoveRule moveRule) {
        this.pieceType = pieceType;
        this.campType = campType;
        this.moveRule = moveRule;
    }

    public boolean compareCamp(final Piece other) {
        return campType == other.campType;
    }

    public boolean isPawn() {
        return pieceType == PieceType.PAWN;
    }

    public boolean isSameCamp(final CampType diffType) {
        return campType == diffType;
    }

    public boolean canMove(final Position source, final Position target) {
        return moveRule.canMove(source, target);
    }

    public boolean canAttack(final Position source, final Position target) {
        return moveRule.canAttack(source, target);
    }

    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return moveRule.isPossibleRoute(source, target, isPossible);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return pieceType == piece.pieceType && campType == piece.campType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, campType);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", campType=" + campType +
                '}';
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public CampType getCampType() {
        return campType;
    }
}
