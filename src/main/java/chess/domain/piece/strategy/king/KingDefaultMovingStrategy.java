package chess.domain.piece.strategy.king;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class KingDefaultMovingStrategy implements KingMovingStrategy {

    private static final List<Direction> DIRECTIONS = List.of(Direction.values());
    private static final int MOVABLE_LENGTH = 2;

    @Override
    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        double distance = calculateDistance(source, target);
        Direction direction = Direction.of(source, target);

        return distance <= MOVABLE_LENGTH
                && DIRECTIONS.contains(direction)
                && (canMoveOnePosition(board, direction, source) || isCapture(board, source, target));
    }

    private int calculateDistance(Position source, Position target) {
        int rankLength = Math.abs(source.getRankIndex() - target.getRankIndex());
        int fileLength = Math.abs(source.getFileIndex() - target.getFileIndex());

        return square(rankLength) + square(fileLength);
    }

    private int square(int value) {
        return value * value;
    }

    private boolean canMoveOnePosition(List<List<Piece>> board, Direction direction, Position source) {
        Piece targetPosition = findPiece(board, source.add(direction));
        return targetPosition.isEmpty();
    }

    private boolean isCapture(List<List<Piece>> board, Position source, Position target) {
        Piece sourcePiece = findPiece(board, source);
        Piece targetPiece = findPiece(board, target);

        return !targetPiece.isEmpty() && !sourcePiece.isSameColor(targetPiece);
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
