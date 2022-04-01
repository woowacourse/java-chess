package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public abstract class PawnMovingStrategy implements MovingStrategy {

    private final int rankIndexStartingPoint;
    private final List<Direction> directions;

    protected PawnMovingStrategy(int rankIndexStartingPoint, List<Direction> directions) {
        this.rankIndexStartingPoint = rankIndexStartingPoint;
        this.directions = directions;
    }

    @Override
    public final void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        MovingInfo movingInfo = new MovingInfo(sourcePosition, targetPosition);

        validateDirection(movingInfo);
        if (canMovingForward(board, sourcePosition, targetPosition, movingInfo)) {
            return;
        }
        if (canCapture(board, targetPosition, movingInfo)) {
            return;
        }
        throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
    }

    private void validateDirection(MovingInfo movingInfo) {
        if (!movingInfo.isContainedDirection(directions)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private boolean canMovingForward(Board board, Position sourcePosition, Position targetPosition,
                                     MovingInfo movingInfo) {
        if (isMovableLengthAtMove(sourcePosition, movingInfo)) {
            Piece pieceInPath = board.findPiece(sourcePosition.add(movingInfo.getDirection()));
            Piece targetPiece = board.findPiece(targetPosition);

            validateMoveTop(sourcePosition, movingInfo, pieceInPath);
            validateExistPiece(targetPiece);
            return true;
        }
        return false;
    }

    private boolean isMovableLengthAtMove(Position sourcePosition, MovingInfo movingInfo) {
        return movingInfo.isSameFileLength(0)
                && (movingInfo.isSameRankLength(1)
                || (isStartingPoint(sourcePosition) && movingInfo.isSameRankLength(2)));
    }

    private boolean isStartingPoint(Position sourcePosition) {
        return sourcePosition.getRankIndex() == rankIndexStartingPoint;
    }

    private void validateMoveTop(Position source, MovingInfo movingInfo, Piece piece) {
        if (movingInfo.isSameRankLength(2) && isStartingPoint(source)) {
            validateExistPiece(piece);
        }
    }

    private void validateExistPiece(Piece piece) {
        if (!piece.isEmpty()) {
            throw new IllegalArgumentException("경로에 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private boolean canCapture(Board board, Position targetPosition, MovingInfo movingInfo) {
        if (isMovableLengthAtCapture(movingInfo)) {
            Piece targetPiece = board.findPiece(targetPosition);

            validateEmptyPiece(targetPiece);
            validateSameColor(targetPiece);
            return true;
        }
        return false;
    }

    private boolean isMovableLengthAtCapture(MovingInfo movingInfo) {
        return movingInfo.isSameRankLength(1) && movingInfo.isSameFileLength(1);
    }

    private void validateEmptyPiece(Piece piece) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("target 위치에 기물이 존재하지 않아 공격할 수 없습니다.");
        }
    }

    abstract void validateSameColor(Piece targetPiece);
}
