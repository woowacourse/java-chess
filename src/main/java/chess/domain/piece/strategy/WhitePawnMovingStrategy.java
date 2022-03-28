package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnMovingStrategy implements MovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 6;
    private static final Direction MOVABLE_DIRECTION = Direction.TOP;
    private static final List<Direction> CAPTURABLE_DIRECTIONS = List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT);

    @Override
    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        int rankLength = Math.abs(sourcePosition.getRankIndex() - targetPosition.getRankIndex());
        int fileLength = Math.abs(sourcePosition.getFileIndex() - targetPosition.getFileIndex());
        Direction direction = Direction.of(sourcePosition, targetPosition);

        if (direction == MOVABLE_DIRECTION && isMovableLengthAtMove(sourcePosition, rankLength)) {
            validateMoveTop(sourcePosition, rankLength, findPiece(board, sourcePosition.add(direction)));
            validateExistPiece(findPiece(board, targetPosition));
            return;
        }
        if (CAPTURABLE_DIRECTIONS.contains(direction) && isMovableLengthAtCapture(rankLength, fileLength)) {
            validateCapture(findPiece(board, targetPosition));
            return;
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
    }

    private boolean isMovableLengthAtMove(Position sourcePosition, int rankLength) {
        return rankLength == 1
                || (sourcePosition.getRankIndex() == RANK_INDEX_STARTING_POINT && rankLength == 2);
    }

    private void validateMoveTop(Position source, int rankLength, Piece piece) {
        if (rankLength == 2 && source.getRankIndex() == RANK_INDEX_STARTING_POINT) {
            validateExistPiece(piece);
        }
    }

    private void validateExistPiece(Piece piece) {
        if (!piece.isEmpty()) {
            throw new IllegalArgumentException("경로에 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private boolean isMovableLengthAtCapture(int rankLength, int fileLength) {
        return rankLength + fileLength == 2;
    }

    private void validateCapture(Piece targetPiece) {
        validateEmptyPiece(targetPiece);
        validateSameColor(targetPiece);
    }

    private void validateEmptyPiece(Piece piece) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("target 위치에 기물이 존재하지 않아 공격할 수 없습니다.");
        }
    }

    private void validateSameColor(Piece targetPiece) {
        if (!targetPiece.isBlack()) {
            throw new IllegalArgumentException("공격은 다른 진영만 가능합니다.");
        }
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
