package chess.domain.piece;

import static chess.domain.piece.position.PositionUtil.isMappedRankIdx;

import chess.domain.piece.position.Position;
import java.util.Objects;

public final class Pawn extends Piece {

    public static final char BLACK_INIT_RANK = '7';
    public static final char WHITE_INIT_RANK = '2';

    private static final String BLACK_DISPLAY = "♗";
    private static final String WHITE_DISPLAY = "♝";

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position position) {
        validateMovablePosition(position);
        this.position = position;
    }

    private void validateMovablePosition(Position position) {
        if (!isMovablePosition(position)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean isMovablePosition(Position position) {
        if (!isSameFile(position) || !isForward(position)) {
            return false;
        }
        return canMoveForwardOrJump(position.getRankIdx());
    }

    private boolean canMoveForwardOrJump(int toRankIdx) {
        int moveCount = Math.abs(currentRankIdx() - toRankIdx);
        if (moveCount == 2) {
            return canJump();
        }
        return moveCount == 1;
    }

    private boolean isSameFile(Position position) {
        return this.position.getFileIdx() == position.getFileIdx();
    }

    private boolean isForward(Position position) {
        int toRankIdx = position.getRankIdx();
        return isWhiteForward(toRankIdx) || isBlackForward(toRankIdx);
    }

    private boolean isWhiteForward(int toRankIdx) {
        return color == Color.WHITE && currentRankIdx() < toRankIdx;
    }

    private boolean isBlackForward(int toRankIdx) {
        return color == Color.BLACK && currentRankIdx() > toRankIdx;
    }

    private boolean canJump() {
        int curRankIdx = position.getRankIdx();
        return isWhiteJump(curRankIdx) || isBlackJump(curRankIdx);
    }

    private boolean isWhiteJump(int curRankIdx) {
        return color == Color.WHITE && isMappedRankIdx(WHITE_INIT_RANK, curRankIdx);
    }

    private boolean isBlackJump(int curRankIdx) {
        return color == Color.BLACK && isMappedRankIdx(BLACK_INIT_RANK, curRankIdx);
    }

    private int currentRankIdx() {
        return position.getRankIdx();
    }

    @Override
    public String display() {
        if (color == Color.BLACK) {
            return BLACK_DISPLAY;
        }
        return WHITE_DISPLAY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pawn pawn = (Pawn) o;
        return color == pawn.color
                && position == pawn.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "color=" + color +
                ", position=" + position +
                '}';
    }
}
