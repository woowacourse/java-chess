package chess.domain.model.piece.shape.strategy.rook;

import chess.domain.model.player.Color;
import chess.domain.model.piece.shape.strategy.PieceStrategy;
import chess.domain.model.position.Position;

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
