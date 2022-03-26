package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.PositionUtil;
import java.util.List;
import java.util.Objects;

public final class Pawn extends Piece {

    public static final char BLACK_INIT_RANK = '7';
    public static final char WHITE_INIT_RANK = '2';

    private static final double DEFAULT_SCORE = 1;

    private static final int MOVE_FILE_COUNT = 0;
    private static final int FORWARD_RANK_COUNT = 1;
    private static final int JUMP_RANK_COUNT = 2;
    private static final int BLACK_MOVE_RANK_DIFFERENCE = -1;
    private static final int ATTACKABLE_FILE_DIFFERENCE = 1;
    private static final int MOVABLE_RANK_DIFFERENCE = 1;

    private static final String BLACK_DISPLAY = "♗";
    private static final String WHITE_DISPLAY = "♝";

    private static final String INVALID_ATTACKABLE_POSITION_EXCEPTION_MESSAGE = "공격할 수 없는 위치입니다.";

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getPositionsInPath(Position toPosition) {
        Direction direction = Direction.findCrossDirection(position, toPosition);
        return direction.findPositionsInPath(position, toPosition);
    }

    @Override
    public void move(Position position) {
        validateMovable(position);
        this.position = position;
    }

    @Override
    public void validateMovable(Position toPosition) {
        if (!canMoveForwardOrJump(toPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean canMoveForwardOrJump(Position toPosition) {
        if (canJump() && toPosition.isSamePosition(getMovablePosition(JUMP_RANK_COUNT))) {
            return true;
        }
        Position forwardPosition = getMovablePosition(FORWARD_RANK_COUNT);
        return toPosition.isSamePosition(forwardPosition);
    }

    private Position getMovablePosition(int moveRankDiff) {
        return position.movedBy(MOVE_FILE_COUNT, moveRankDifference(moveRankDiff));
    }

    private int moveRankDifference(int moveCount) {
        if (color == Color.BLACK) {
            return moveCount * BLACK_MOVE_RANK_DIFFERENCE;
        }
        return moveCount;
    }

    private boolean canJump() {
        int curRankIdx = position.getRankIdx();
        return isWhiteJump(curRankIdx) || isBlackJump(curRankIdx);
    }

    private boolean isWhiteJump(int curRankIdx) {
        return color.isWhite() && PositionUtil.isMappedRankIdx(WHITE_INIT_RANK, curRankIdx);
    }

    private boolean isBlackJump(int curRankIdx) {
        return color.isBlack() && PositionUtil.isMappedRankIdx(BLACK_INIT_RANK, curRankIdx);
    }

    @Override
    protected void attack(Position enemyPosition) {
        validateAttackable(enemyPosition);
        this.position = enemyPosition;
    }

    private void validateAttackable(Position enemyPosition) {
        if (!canAttack(enemyPosition)) {
            throw new IllegalArgumentException(INVALID_ATTACKABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean canAttack(Position enemyPosition) {
        int fileDifference = position.fileDifference(enemyPosition);
        int rankRawDifference = position.rankRawDifference(enemyPosition);

        return position.isDiagonal(enemyPosition)
            && fileDifference == ATTACKABLE_FILE_DIFFERENCE
            && rankRawDifference == moveRankDifference(MOVABLE_RANK_DIFFERENCE);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return DEFAULT_SCORE;
    }

    @Override
    public String display() {
        if (color.isBlack()) {
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
