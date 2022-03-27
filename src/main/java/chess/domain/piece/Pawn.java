package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.strategy.OccupiedChecker;
import java.util.Objects;

public final class Pawn extends Chessmen {

    private static final int BLACK_INIT_RANK = 6;
    private static final int WHITE_INIT_RANK = 1;

    private static final double DEFAULT_SCORE = 1;

    private static final int MOVE_FILE_COUNT = 0;
    private static final int FORWARD_RANK_COUNT = 1;
    private static final int JUMP_RANK_COUNT = 2;

    private static final String BLACK_DISPLAY = "♗";
    private static final String WHITE_DISPLAY = "♝";

    private static final String INVALID_ATTACKABLE_POSITION_EXCEPTION_MESSAGE = "공격할 수 없는 위치입니다.";

    Pawn(Color color, Position position) {
        super(color, position);
    }

    public static Pawn of(Color color, int fileIdx) {
        if (color == BLACK) {
            return Pawn.ofBlack(fileIdx);
        }
        return Pawn.ofWhite(fileIdx);
    }

    static Pawn ofBlack(int fileIdx) {
        Position position = Position.of(fileIdx, BLACK_INIT_RANK);
        return new Pawn(BLACK, position);
    }

    static Pawn ofWhite(int fileIdx) {
        Position position = Position.of(fileIdx, WHITE_INIT_RANK);
        return new Pawn(WHITE, position);
    }

    @Override
    protected boolean canMoveTo(Position targetPosition) {
        if (canJump() && targetPosition == getMovablePosition(JUMP_RANK_COUNT)) {
            return true;
        }
        Position forwardPosition = getMovablePosition(FORWARD_RANK_COUNT);
        return targetPosition == forwardPosition;
    }

    private Position getMovablePosition(int moveRankDiff) {
        return position.movedBy(MOVE_FILE_COUNT, moveRankDifference(moveRankDiff));
    }

    private int moveRankDifference(int moveCount) {
        if (color == BLACK) {
            return moveCount * -1;
        }
        return moveCount;
    }

    private boolean canJump() {
        boolean isWhiteJump = color == WHITE && position.hasRankIdxOf(WHITE_INIT_RANK);
        boolean isBlackJump = color == BLACK && position.hasRankIdxOf(BLACK_INIT_RANK);

        return isWhiteJump || isBlackJump;
    }

    @Override
    protected void attack(Position enemyPosition, OccupiedChecker isOccupied) {
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

        return position.isDiagonal(enemyPosition) &&
                fileDifference == 1
                && rankRawDifference == moveRankDifference(1);
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
        if (color == BLACK) {
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
        return "Pawn{color=" + color + ", position=" + position + '}';
    }
}
