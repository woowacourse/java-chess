package chess.domain.piece;

import static chess.domain.piece.position.PositionUtil.isMappedRankIdx;

import chess.domain.piece.position.Position;
import java.util.Objects;

public final class Pawn extends Piece {

    public static final char BLACK_INIT_RANK = '7';
    public static final char WHITE_INIT_RANK = '2';

    private static final int MOVE_FILE_COUNT = 0;
    private static final int FORWARD_RANK_COUNT = 1;
    private static final int JUMP_RANK_COUNT = 2;

    private static final String BLACK_DISPLAY = "♗";
    private static final String WHITE_DISPLAY = "♝";

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position toPosition) {
        validateMovable(toPosition);
        position = toPosition;
    }

    private void validateMovable(Position toPosition) {
        if (!canMoveForwardOrJump(toPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean canMoveForwardOrJump(Position toPosition) {
        if (canJump() && toPosition == getMovablePosition(JUMP_RANK_COUNT)) {
            return true;
        }
        Position forwardPosition = getMovablePosition(FORWARD_RANK_COUNT);
        return toPosition == forwardPosition;
    }

    private Position getMovablePosition(int moveRankDiff) {
        return position.movedBy(MOVE_FILE_COUNT, moveRankDifference(moveRankDiff));
    }

    private int moveRankDifference(int moveCount) {
        if (color == Color.BLACK) {
            return moveCount * -1;
        }
        return moveCount;
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
