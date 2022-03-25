package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnMovingStrategy implements MovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 6;
    private static final Direction MOVABLE_DIRECTION = Direction.TOP;
    private static final List<Direction> CAPTURABLE_DIRECTIONS = List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT);

    @Override
    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.of(sourcePosition, targetPosition);
        validateDirection(direction);

        int absRankIndex = Math.abs(sourcePosition.getRankIndex() - targetPosition.getRankIndex());
        int absFileIndex = Math.abs(sourcePosition.getFileIndex() - targetPosition.getFileIndex());

        if (direction == MOVABLE_DIRECTION) {
            if (sourcePosition.getRankIndex() == RANK_INDEX_STARTING_POINT && absRankIndex == 2) {
                validateExistPieces(board, sourcePosition, targetPosition, direction);
                return;
            }

            if (absRankIndex != 1) {
                throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
            }

            validateExistPiece(findPiece(board, targetPosition));
        }

        if (CAPTURABLE_DIRECTIONS.contains(direction)) {
            if(absRankIndex + absFileIndex != 2) {
                throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
            }
            Piece targetPiece = findPiece(board, targetPosition);
            if (targetPiece.isEmpty()) {
                throw new IllegalArgumentException("target 위치에 기물이 존재하지 않아 공격할 수 없습니다.");
            }
            if(!targetPiece.isBlack()) {
                throw new IllegalArgumentException("공격은 다른 진영만 가능합니다.");
            }
        }
    }

    private void validateDirection(Direction direction) {
        if (!(direction == MOVABLE_DIRECTION || CAPTURABLE_DIRECTIONS.contains(direction))) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private void validateExistPieces(List<List<Piece>> board, Position sourcePosition, Position targetPosition, Direction direction) {
        Position currentPosition = sourcePosition;
        while (!currentPosition.equals(targetPosition)) {
            currentPosition = currentPosition.add(direction);
            Piece currentPiece = findPiece(board, currentPosition);
            validateExistPiece(currentPiece);
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
