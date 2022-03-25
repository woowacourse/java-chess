package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class LinearMovingStrategy implements MovingStrategy {

    private final List<Direction> directions;

    public LinearMovingStrategy(List<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.of(sourcePosition, targetPosition);
        validateDirection(direction);

        Position currentPosition = sourcePosition;
        while (!currentPosition.equals(targetPosition)) {
            currentPosition = currentPosition.add(direction);
            Piece currentPiece = findPiece(board, currentPosition);
            validateExistPiece(currentPiece);
        }
    }

    private void validateDirection(Direction direction) {
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private void validateExistPiece(Piece currentPiece) {
        if (!currentPiece.isEmpty()) {
            throw new IllegalArgumentException("경로에 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
