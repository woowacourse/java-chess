package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
import java.util.List;

public class Pawn extends Piece {
    private final List<Direction> possibleDirections;
    private final boolean isMoved;

    public Pawn(Team team) {
        super(team, PieceType.PAWN);
        possibleDirections = makePossibleMove();
        isMoved = false;
    }

    public Pawn(Team team, boolean isMoved) {
        super(team, PieceType.PAWN);
        possibleDirections = makePossibleMove();
        this.isMoved = isMoved;
    }

    private List<Direction> makePossibleMove() {
        if (team.equals(Team.WHITE)) {
            return List.of(Direction.UP, Direction.RIGHT_UP, Direction.LEFT_UP);
        }
        return List.of(Direction.DOWN, Direction.RIGHT_DOWN, Direction.LEFT_DOWN);
    }

    @Override
    public void validateMovableRange(Square source, Square target) {
        Direction direction = Direction.calculateDirection(source, target);

        if (!possibleDirections.contains(direction)) {
            throw new PieceCanNotMoveException();
        }
        if (!validateMovableRange(source, target, direction)) {
            throw new PieceCanNotMoveException();
        }
    }

    private boolean validateMovableRange(Square source, Square target, Direction direction) {
        boolean isMovableOneStep = source.isMovableToTarget(target, direction.getFile(), direction.getRank());
        boolean isMovableTwoStep = source.isMovableToTarget(target, direction.getFile(),
                direction.getRank() + team.calculateDirection(1));

        if (Direction.isMoveForward(direction) && !isMoved) {
            return isMovableOneStep || isMovableTwoStep;
        }
        return isMovableOneStep;
    }
}
