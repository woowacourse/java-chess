package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

public class KnightMovingStrategy implements MovingStrategy {

    @Override
    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        int absRankIndex = Math.abs(sourcePosition.getRankIndex() - targetPosition.getRankIndex());
        int absFileIndex = Math.abs(sourcePosition.getFileIndex() - targetPosition.getFileIndex());

        if (square(absRankIndex) + square(absFileIndex) != 5) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private int square(int value) {
        return value * value;
    }
}
