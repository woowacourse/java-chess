package chess.domain.strategy.bishop;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.strategy.PieceStrategy;

public class BishopStrategy implements PieceStrategy {

    @Override
    public void validateDirection(final Position source, final Position target, final Color sourceColor,
                                  final boolean doesTargetPositionHavePiece) {
        int fileDistance = Math.abs(source.getFileValue() - target.getFileValue());
        int rankDistance = Math.abs(source.getRankValue() - target.getRankValue());

        if (fileDistance != rankDistance) {
            throw new IllegalArgumentException("대각선 방향이 아닙니다.");
        }
    }

}
