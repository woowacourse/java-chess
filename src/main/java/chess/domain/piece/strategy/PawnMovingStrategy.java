package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public abstract class PawnMovingStrategy implements MovingStrategy {

    private final int rankIndexStartingPoint;
    private final List<Direction> movableDirection;

    protected PawnMovingStrategy(int rankIndexStartingPoint, List<Direction> movableDirection) {
        this.rankIndexStartingPoint = rankIndexStartingPoint;
        this.movableDirection = movableDirection;
    }

    @Override
    public final void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        int rankLength = Math.abs(sourcePosition.getRankIndex() - targetPosition.getRankIndex());
        int fileLength = Math.abs(sourcePosition.getFileIndex() - targetPosition.getFileIndex());
        Direction direction = Direction.of(sourcePosition, targetPosition);

        validateDirectionAndLength(sourcePosition, rankLength, fileLength, direction);
        if (isMovableLengthAtMove(sourcePosition, rankLength, fileLength)) {
            validateMoveTop(sourcePosition, rankLength, findPiece(board, sourcePosition.add(direction)));
            validateExistPiece(findPiece(board, targetPosition));
        }
        if (isMovableLengthAtCapture(rankLength, fileLength)) {
            validateCapture(findPiece(board, targetPosition));
        }
    }

    private void validateDirectionAndLength(Position sourcePosition, int rankLength, int fileLength,
                                            Direction direction) {
        if (!(movableDirection.contains(direction) && (isMovableLengthAtMove(sourcePosition, rankLength, fileLength)
                || isMovableLengthAtCapture(rankLength, fileLength)))) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private boolean isMovableLengthAtMove(Position sourcePosition, int rankLength, int fileLength) {
        return fileLength == 0 && (rankLength == 1 || (isStartingPoint(sourcePosition) && rankLength == 2));
    }

    private boolean isStartingPoint(Position sourcePosition) {
        return sourcePosition.getRankIndex() == rankIndexStartingPoint;
    }

    private void validateMoveTop(Position source, int rankLength, Piece piece) {
        if (rankLength == 2 && isStartingPoint(source)) {
            validateExistPiece(piece);
        }
    }

    private void validateExistPiece(Piece piece) {
        if (!piece.isEmpty()) {
            throw new IllegalArgumentException("경로에 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private boolean isMovableLengthAtCapture(int rankLength, int fileLength) {
        return fileLength == 1 && rankLength == 1;
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

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }

    abstract void validateSameColor(Piece targetPiece);
}
