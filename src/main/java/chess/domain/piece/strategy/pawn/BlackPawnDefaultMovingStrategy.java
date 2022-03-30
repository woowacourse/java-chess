package chess.domain.piece.strategy.pawn;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class BlackPawnDefaultMovingStrategy implements MovingStrategy {

    private static final Direction MOVABLE_DIRECTION = Direction.BOTTOM;

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        Direction direction = Direction.of(source, target);
        double distance = Math.sqrt(source.calculateDistance(target));

        return direction == MOVABLE_DIRECTION && canMoveOnePosition(board, distance, source);
    }

    private boolean canMoveOnePosition(Board board, double distance, Position source) {
        Piece targetPosition = board.findPiece(source.add(MOVABLE_DIRECTION));
        return distance == 1 && targetPosition.isEmpty();
    }
}
