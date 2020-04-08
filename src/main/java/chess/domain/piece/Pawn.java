package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.TeamStrategy;

import java.util.List;

public class Pawn extends Piece {
    private static final int PAWN_NORMAL_MOVE_RANGE = 1;
    private static final int PAWN_START_LINE_MOVE_RANGE = 2;

    public Pawn(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    protected boolean isMovablePattern(Move move, Position targetPosition, List<Piece> pieces) {
        return pieces.stream()
                .filter(x -> x.isEqualPosition(targetPosition))
                .findAny()
                .map(piece -> isAttackMovePattern(move))
                .orElseGet(() -> isNotAttackMovePattern(move));
    }

    private boolean isAttackMovePattern(Move move) {
        Direction rightAttackDirection = Direction.DOWN_RIGHT;
        Direction leftAttackDirection = Direction.DOWN_LEFT;
        if (teamStrategy.isBlackTeam()) {
            rightAttackDirection = Direction.UP_RIGHT;
            leftAttackDirection = Direction.UP_LEFT;
        }

        return isAttack(move, rightAttackDirection, leftAttackDirection);
    }

    private boolean isAttack(Move move, Direction rightAttackDirection, Direction leftAttackDirection) {
        return (move.getDirection() == rightAttackDirection || move.getDirection() == leftAttackDirection)
                && move.getCount() == PAWN_NORMAL_MOVE_RANGE;
    }

    private boolean isNotAttackMovePattern(Move move) {
        if (this.position.isPawnStartLine(this)) {
            return isValidMove(move, PAWN_START_LINE_MOVE_RANGE);
        }
        return isValidMove(move, PAWN_NORMAL_MOVE_RANGE);
    }

    private boolean isValidMove(Move move, int validRange) {
        Direction forwardDirection = Direction.DOWN;
        if (teamStrategy.isBlackTeam()) {
            forwardDirection = Direction.UP;
        }

        return move.getDirection() == forwardDirection
                && move.getCount() <= validRange;
    }

    @Override
    public String getPieceName() {
        return teamStrategy.pawnName();
    }
}
