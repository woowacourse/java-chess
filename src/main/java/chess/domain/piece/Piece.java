package chess.domain.piece;

import chess.domain.chess.CampType;
import chess.domain.piece.move.piece.MoveRule;

import java.util.Objects;

public class Piece {
    private static final int PAWN_FIRST_MOVE = 1;
    private static final int WHITE_PAWN_FIRST_MOVE = 1;
    private static final int BLACK_PAWN_FIRST_MOVE = 6;

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

    public boolean isSameCamp(final CampType diffType) {
        return campType == diffType;
    }

    public boolean canMove(final Position source, final Position target) {
        if (pieceType == PieceType.PAWN && moveRule.canMove(source, target)) {
            return validatePawnFirstMove(source, target);
        }
        return moveRule.canMove(source, target);
    }

    private boolean validatePawnFirstMove(final Position source, final Position target) {
        if ((isSameCamp(CampType.WHITE) && source.isRankSame(WHITE_PAWN_FIRST_MOVE)) ||
                (isSameCamp(CampType.BLACK) && source.isRankSame(BLACK_PAWN_FIRST_MOVE))) {
            return true;
        }
        if (Math.abs(target.calculateRankGap(source)) != PAWN_FIRST_MOVE) {
            throw new IllegalArgumentException("폰은 처음 이후 1칸만 전진할 수 있습니다.");
        }
        return true;
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
