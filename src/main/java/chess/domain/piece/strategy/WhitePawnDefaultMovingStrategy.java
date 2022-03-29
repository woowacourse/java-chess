package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnDefaultMovingStrategy {

    private static final Direction MOVABLE_DIRECTION = Direction.TOP;

    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        Direction direction = Direction.of(source, target);
        double distance = calculateDistance(source, target);

        return direction == MOVABLE_DIRECTION && canMoveOnePosition(board, distance, source);
    }

    private double calculateDistance(Position source, Position target) {
        int rankLength = Math.abs(source.getRankIndex() - target.getRankIndex());
        int fileLength = Math.abs(source.getFileIndex() - target.getFileIndex());

        return Math.sqrt(square(rankLength) + square(fileLength));
    }

    private int square(int value) {
        return value * value;
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
