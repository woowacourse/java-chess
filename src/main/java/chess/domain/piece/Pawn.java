package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import java.util.List;

public class Pawn extends Piece {
    private final List<Direction> possibleDirections;
    private final boolean isMoved;

    public Pawn(final Team team) {
        super(team, Role.PAWN);
        this.possibleDirections = this.makePossibleMove();
        this.isMoved = false;
    }

    public Pawn(final Team team, final boolean isMoved) {
        super(team, Role.PAWN);
        this.possibleDirections = this.makePossibleMove();
        this.isMoved = isMoved;
    }

    private List<Direction> makePossibleMove() {
        if (this.team.equals(Team.WHITE)) {
            return List.of(Direction.UP, Direction.RIGHT_UP, Direction.LEFT_UP);
        }
        return List.of(Direction.DOWN, Direction.RIGHT_DOWN, Direction.LEFT_DOWN);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Direction direction) {
        if (this.possibleDirections.contains(direction)) {
            return this.isMovableContainPossibleMoves(source, target, direction);
        }
        return false;
    }

    private boolean isMovableContainPossibleMoves(final Square source, final Square target, final Direction direction) {
        final boolean isMovableOneStep = source.isMovableToTarget(target, direction.getFile(), direction.getRank());
        final boolean isMovableTwoStep = source.isMovableToTarget(target, direction.getFile(),
                direction.getRank() + this.team.calculateDirection(1));
        if (Direction.isMoveForward(direction) && !this.isMoved) {
            return isMovableOneStep || isMovableTwoStep;
        }
        return isMovableOneStep;
    }
}
