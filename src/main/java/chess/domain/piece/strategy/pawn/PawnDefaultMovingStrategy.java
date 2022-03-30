package chess.domain.piece.strategy.pawn;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class PawnDefaultMovingStrategy implements MovingStrategy {

    private final Direction direction;

    public PawnDefaultMovingStrategy(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        Direction direction = Direction.of(source, target);
        double distance = Math.sqrt(source.calculateDistance(target));

        return direction == direction && canMoveOnePosition(board, distance, source);
    }

    private boolean canMoveOnePosition(Board board, double distance, Position source) {
        Piece targetPosition = board.findPiece(source.add(direction));
        return distance == 1 && targetPosition.isEmpty();
    }
}
