package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.Square;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;

import java.util.stream.Stream;

public class HasHindranceInTwoStep implements CanNotMoveStrategy {
    private final Direction forwardDirection;
    private static final int MAX_DISTANCE = 2;

    public HasHindranceInTwoStep(Direction forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    //todo: refac
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        if (from.isDiagonalDirection(to)) {
            return false;
        }

        Distance amount = from.calculateDistance(to);
        Direction direction = from.calculateDirection(to);

        return piecesState.hasHindranceInBetween(amount, direction, from);
    }

//    private boolean hasHindrance(Position from, PiecesState piecesState) {
//        Position forwardPosition = from.go(forwardDirection);
//        return Stream.iterate(forwardPosition, position -> position.go(forwardDirection))
//                .limit(MAX_DISTANCE)
//                .map(piecesState::getPiece)
//                .anyMatch(Square::isFilled);
//    }
}
