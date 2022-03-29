package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnStartingPointMovingStrategy implements MovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 6;
    private static final Direction MOVABLE_DIRECTION = Direction.TOP;

    @Override
    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        Direction direction = Direction.of(source, target);
        double distance = source.calculateDistance(target);

        return direction == MOVABLE_DIRECTION
                && (canMoveTwoPosition(board, distance, source) || canMoveOnePosition(board, distance, source));
    }

    private boolean canMoveTwoPosition(List<List<Piece>> board, double distance, Position source) {
        Position currentPosition = source.add(MOVABLE_DIRECTION);
        Piece currentPiece = findPiece(board, currentPosition);

        currentPosition = currentPosition.add(MOVABLE_DIRECTION);
        Piece targetPiece = findPiece(board, currentPosition);

        return source.getRankIndex() == RANK_INDEX_STARTING_POINT
                && distance == 4
                && currentPiece.isEmpty()
                && targetPiece.isEmpty();
    }

    private boolean canMoveOnePosition(List<List<Piece>> board, double distance, Position source) {
        Piece targetPosition = findPiece(board, source.add(MOVABLE_DIRECTION));

        return source.getRankIndex() == RANK_INDEX_STARTING_POINT
                && distance == 1
                && targetPosition.isEmpty();
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
