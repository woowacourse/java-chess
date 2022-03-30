package chess.domain.piece.strategy.pawn;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class PawnStartingPointMovingStrategy implements MovingStrategy {

    private final int startIndex;
    private final Direction direction;

    public PawnStartingPointMovingStrategy(int startIndex, Direction direction) {
        this.startIndex = startIndex;
        this.direction = direction;
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        Direction direction = Direction.of(source, target);
        double distance = source.calculateDistance(target);

        return this.direction == direction
                && (canMoveTwoPosition(board, distance, source) || canMoveOnePosition(board, distance, source));
    }

    private boolean canMoveTwoPosition(Board board, double distance, Position source) {
        Position currentPosition = source.add(direction);
        Piece currentPiece = board.findPiece(currentPosition);

        currentPosition = currentPosition.add(direction);
        Piece targetPiece = board.findPiece(currentPosition);

        return source.getRankIndex() == startIndex
                && distance == 4
                && currentPiece.isEmpty()
                && targetPiece.isEmpty();
    }

    private boolean canMoveOnePosition(Board board, double distance, Position source) {
        Piece targetPosition = board.findPiece(source.add(direction));

        return source.getRankIndex() == startIndex
                && distance == 1
                && targetPosition.isEmpty();
    }
}
