package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.PositionConverter;
import java.util.List;
import java.util.Objects;

public final class Pawn extends Piece {

    private static final String NAME = "Pawn";

    private static final double DEFAULT_SCORE = 1;

    public static final char BLACK_INIT_RANK = '7';
    public static final char WHITE_INIT_RANK = '2';

    private static final int MOVABLE_RANK_DIFFERENCE = 1;
    private static final int JUMPABLE_RANK_DIFFERENCE = 2;

    private static final String INVALID_ATTACKABLE_POSITION_EXCEPTION_MESSAGE = "공격할 수 없는 위치입니다.";

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getPositionsInPath(Position toPosition) {
        Direction direction = Direction.findDirection(position, toPosition);
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
        if (color.isWhite()) {
            return validateWhiteMovable(toPosition);
        }
        return validateBlackMovable(toPosition);
    }

    private boolean validateWhiteMovable(Position toPosition) {
        if (!isValidWhiteDirection(toPosition)) {
            return false;
        }
        if (isWhiteInJumpablePosition(position.getRankIdx()) && isValidRankDifferenceForJumping(toPosition)) {
            return true;
        }
        return isValidRankDifferenceForMoveOneStep(toPosition);
    }

    private boolean isValidWhiteDirection(Position toPosition) {
        Direction direction = Direction.findDirection(position, toPosition);
        return direction.isAttachedUpDirection();
    }

    private boolean isValidRankDifferenceForMoveOneStep(Position toPosition) {
        return position.rankDifference(toPosition) == MOVABLE_RANK_DIFFERENCE;
    }

    private boolean isValidRankDifferenceForJumping(Position toPosition) {
        return position.rankDifference(toPosition) == JUMPABLE_RANK_DIFFERENCE;
    }

    private boolean isWhiteInJumpablePosition(int curRankIdx) {
        return PositionConverter.isMappedRankIdx(WHITE_INIT_RANK, curRankIdx);
    }

    private boolean validateBlackMovable(Position toPosition) {
        if (!isValidBlackDirection(toPosition)) {
            return false;
        }
        if (isBlackInJumpablePosition(position.getRankIdx()) && isValidRankDifferenceForJumping(toPosition)) {
            return true;
        }
        return isValidRankDifferenceForMoveOneStep(toPosition);
    }

    private boolean isValidBlackDirection(Position toPosition) {
        Direction direction = Direction.findDirection(position, toPosition);
        return direction.isAttachedDownDirection();
    }


    private boolean isBlackInJumpablePosition(int curRankIdx) {
        return PositionConverter.isMappedRankIdx(BLACK_INIT_RANK, curRankIdx);
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
        Direction direction = Direction.findDirection(position, enemyPosition);
        if (color.isWhite()) {
            return direction.isUpwardDiagonalDirection();
        }
        return direction.isDownwardDiagonalDirection();
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
    public String name() {
        return NAME;
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
