package chess.domain.piece.strategy.pawn;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class BlackPawnStartingPointMovingStrategy implements MovingStrategy {

    private static int RANK_INDEX_STARTING_POINT = 1;
    private static final Direction MOVABLE_DIRECTION = Direction.BOTTOM;

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        Direction direction = Direction.of(source, target);
        double distance = source.calculateDistance(target);

        return direction == MOVABLE_DIRECTION
                && (canMoveTwoPosition(board, distance, source) || canMoveOnePosition(board, distance, source));
    }

    private boolean canMoveTwoPosition(Board board, double distance, Position source) {
        Position currentPosition = source.add(MOVABLE_DIRECTION);
        Piece currentPiece = board.findPiece(currentPosition);

        currentPosition = currentPosition.add(MOVABLE_DIRECTION);
        Piece targetPiece = board.findPiece(currentPosition);

        return source.getRankIndex() == RANK_INDEX_STARTING_POINT
                && distance == 4
                && currentPiece.isEmpty()
                && targetPiece.isEmpty();
    }

    private boolean canMoveOnePosition(Board board, double distance, Position source) {
        Piece targetPosition = board.findPiece(source.add(MOVABLE_DIRECTION));

        return source.getRankIndex() == RANK_INDEX_STARTING_POINT
                && distance == 1
                && targetPosition.isEmpty();
    }
}
