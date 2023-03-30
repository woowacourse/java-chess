package chess.domain.strategy.knight;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.strategy.PieceStrategy;

public class KnightStrategy implements PieceStrategy {

    public void validateDirection(final Position source, final Position target, final Color sourceColor,
                                  final boolean doesTargetPositionHavePiece) {
        int rankDistance = Math.abs(source.getRankValue() - target.getRankValue());
        int fileDistance = Math.abs(source.getFileValue() - target.getFileValue());

        if (!isValid(rankDistance, fileDistance)) {
            throw new IllegalArgumentException("나이트가 움직일 수 있는 곳이 아닙니다.");
        }
    }

    boolean isValid(final int rankDistance, final int fileDistance) {
        return rankDistance == 1 && fileDistance == 2
                || rankDistance == 2 && fileDistance == 1;
    }

}
