package chess.domain.piece.strategy;

import chess.domain.piece.Direction;
import chess.domain.square.Square;

public class PawnMoveStrategy implements MoveStrategy {

    private final PawnDirection moveDirection;

    public PawnMoveStrategy(final PawnDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public boolean movable(Square current, Square destination) {
        if (isMoveForwardTwoSquares(current, destination)) {
            return true;
        }
        return moveDirection.getMovableDirections()
                .stream()
                .map(current::move)
                .anyMatch(destination::equals);
    }

    public boolean isMoveForwardTwoSquares(Square current, Square destination) {
        if (moveDirection.equals(PawnDirection.UPPER)) {
            return isMoveForwardTwoSquaresWhenUpper(current, destination);
        }
        return isMoveForwardTwoSquaresWhenLower(current, destination);
    }

    private boolean isMoveForwardTwoSquaresWhenUpper(final Square current, final Square destination) {
        Square movedOnce = current.move(Direction.UP);
        Square movedTwice = movedOnce.move(Direction.UP);
        return current.isRankTwo() && movedTwice.equals(destination);
    }

    private boolean isMoveForwardTwoSquaresWhenLower(final Square current, final Square destination) {
        Square movedOnce = current.move(Direction.DOWN);
        Square movedTwice = movedOnce.move(Direction.DOWN);
        return current.isRankSeven() && movedTwice.equals(destination);
    }

    @Override
    public void move(Square current, Square destination) {

    }
}
