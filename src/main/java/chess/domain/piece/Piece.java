package chess.domain.piece;

import chess.domain.chess.CampType;
import chess.domain.piece.move.Position;

import java.util.Objects;

public class Piece {
    private static final int PAWN_FIRST_MOVE = 1;
    private static final int WHITE_PAWN_FIRST_MOVE = 1;
    private static final int BLACK_PAWN_FIRST_MOVE = 6;

    private final PieceType pieceType;
    private final CampType campType;
    protected final Movable movable;

    public Piece(final PieceType pieceType, final CampType campType, final Movable movable) {
        this.pieceType = pieceType;
        this.campType = campType;
        this.movable = movable;
    }

    public boolean isSameCamp(final Piece other) {
        return campType == other.campType;
    }

    public boolean isSameCamp(final CampType diffType) {
        return campType == diffType;
    }

    public boolean canMove(final Position source, final Position target, final boolean isTargetExist) {
        if (pieceType == PieceType.PAWN && movable.canMove(source, target)) {
            return validatePawnMove(source, target, isTargetExist);
        }
        return movable.canMove(source, target);
    }

    public boolean canAttack(final Position source, final Position target, final boolean isTargetExist) {
        if (pieceType == PieceType.PAWN && movable.canAttack(source, target) && !isTargetExist) {
            throw new IllegalArgumentException("폰이 공격할 수 있는 위치가 아닙니다.");
        }
        return movable.canAttack(source, target);
    }

    public boolean isKing() {
        return pieceType == PieceType.KING;
    }

    public boolean isPawn() {
        return pieceType == PieceType.PAWN;
    }
    
    public Score getScore() {
        return pieceType.getScore();
    }

    private boolean validatePawnMove(final Position source, final Position target, final boolean isTargetExist) {
        if (isPawnFirstMove(source)) {
            return !isTargetExist;
        }
        return validatePawnOneMove(source, target, isTargetExist);
    }

    private boolean isPawnFirstMove(final Position source) {
        return (isSameCamp(CampType.WHITE) && source.isRankSame(WHITE_PAWN_FIRST_MOVE)) ||
                (isSameCamp(CampType.BLACK) && source.isRankSame(BLACK_PAWN_FIRST_MOVE));
    }

    private boolean validatePawnOneMove(final Position source, final Position target, final boolean isTargetExist) {
        if (Math.abs(target.calculateRankGap(source)) != PAWN_FIRST_MOVE) {
            throw new IllegalArgumentException("폰은 처음 이후 1칸만 전진할 수 있습니다.");
        }
        return !isTargetExist;
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
