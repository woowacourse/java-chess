package chess.domain.strategy.rook;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.strategy.PieceStrategy;

public class RookStrategy implements PieceStrategy {

    @Override
    public void validateDirection(final Position source, final Position target, final Color sourceColor,
                                  final boolean doesTargetPositionHavePiece) {
        boolean sameRank = source.getRankValue() == target.getRankValue();
        boolean sameFile = source.getFileValue() == target.getFileValue();

        if (!sameRank && !sameFile) {
            throw new IllegalArgumentException("룩은 상하좌우로만 움직일 수 있습니다.");
        }
    }

}
