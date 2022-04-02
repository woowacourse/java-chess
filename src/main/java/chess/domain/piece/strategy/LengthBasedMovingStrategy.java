package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.function.IntPredicate;

public class LengthBasedMovingStrategy implements MovingStrategy {

    private final IntPredicate condition;

    public LengthBasedMovingStrategy(IntPredicate condition) {
        this.condition = condition;
    }

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        validateTargetPosition(sourcePosition, targetPosition);
        validateSameColor(board, sourcePosition, targetPosition);
    }

    private void validateTargetPosition(Position sourcePosition, Position targetPosition) {
        int rankLength = Math.abs(sourcePosition.getRankIndex() - targetPosition.getRankIndex());
        int fileLength = Math.abs(sourcePosition.getFileIndex() - targetPosition.getFileIndex());

        if (condition.test(square(rankLength) + square(fileLength))) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private int square(int value) {
        return value * value;
    }

    private void validateSameColor(Board board, Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("같은 진영 기물은 공격할 수 없습니다.");
        }
    }
}
