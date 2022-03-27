package chess.domain.piece.pawn;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Position.LEFT_END_FILE_IDX;
import static chess.domain.piece.Position.RIGHT_END_FILE_IDX;

import chess.domain.piece.Chessmen;
import chess.domain.piece.Color;
import chess.domain.piece.Position;
import chess.strategy.OccupiedChecker;
import java.util.Objects;

public abstract class Pawn extends Chessmen {

    private static final double DEFAULT_SCORE = 1;

    private static final int MOVE_FILE_DIFF = 0;
    private static final int FORWARD_RANK_DIFF = 1;
    protected static final int JUMP_RANK_DIFF = 2;

    private static final int LEFT_ATTACK_FILE_DIFF = -1;
    private static final int RIGHT_ATTACK_FILE_DIFF = 1;
    private static final int ATTACK_RANK_DIFF = 1;

    private static final String INVALID_ATTACKABLE_POSITION_EXCEPTION_MESSAGE = "공격할 수 없는 위치입니다.";

    Pawn(Color color, Position position) {
        super(color, position);
    }

    public static Pawn of(Color color, int fileIdx) {
        if (color == BLACK) {
            return new BlackPawn(fileIdx);
        }
        return new WhitePawn(fileIdx);
    }

    @Override
    protected boolean canMoveTo(Position targetPosition) {
        if (canJump(targetPosition)) {
            return true;
        }
        Position forwardPosition = getMovablePosition(FORWARD_RANK_DIFF);
        return targetPosition == forwardPosition;
    }

    private boolean canJump(Position targetPosition) {
        if (hasMoved()) {
            return false;
        }
        Position jumpPosition = getMovablePosition(JUMP_RANK_DIFF);
        return targetPosition == jumpPosition;
    }

    abstract protected boolean hasMoved();

    protected Position getMovablePosition(int moveRankDiff) {
        return position.movedBy(MOVE_FILE_DIFF, forwardRankDiff(moveRankDiff));
    }

    abstract protected int forwardRankDiff(int rankDiff);

    @Override
    protected void attack(Position enemyPosition, OccupiedChecker isOccupied) {
        validateAttackable(enemyPosition);
        this.position = enemyPosition;
    }

    private void validateAttackable(Position enemyPosition) {
        if (!isLeftAttack(enemyPosition) && !isRightAttack(enemyPosition)) {
            throw new IllegalArgumentException(INVALID_ATTACKABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean isLeftAttack(Position enemyPosition) {
        if (position.hasFileIdxOf(LEFT_END_FILE_IDX)) {
            return false;
        }
        Position leftAttackPosition = position.movedBy(LEFT_ATTACK_FILE_DIFF, forwardRankDiff(ATTACK_RANK_DIFF));
        return enemyPosition == leftAttackPosition;
    }

    private boolean isRightAttack(Position enemyPosition) {
        if (position.hasFileIdxOf(RIGHT_END_FILE_IDX)) {
            return false;
        }
        Position rightAttackPosition = position.movedBy(RIGHT_ATTACK_FILE_DIFF, forwardRankDiff(ATTACK_RANK_DIFF));
        return enemyPosition == rightAttackPosition;
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
}
