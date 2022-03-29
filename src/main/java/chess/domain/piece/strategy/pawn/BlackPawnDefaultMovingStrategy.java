package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class BlackPawnDefaultMovingStrategy implements MovingStrategy {

    private static final Direction MOVABLE_DIRECTION = Direction.BOTTOM;

    @Override
    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        Direction direction = Direction.of(source, target);
        double distance = Math.sqrt(source.calculateDistance(target));

        return direction == MOVABLE_DIRECTION && canMoveOnePosition(board, distance, source);
    }

    private boolean canMoveOnePosition(List<List<Piece>> board, double distance, Position source) {
        Piece targetPosition = findPiece(board, source.add(MOVABLE_DIRECTION));
        return distance == 1 && targetPosition.isEmpty();
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
