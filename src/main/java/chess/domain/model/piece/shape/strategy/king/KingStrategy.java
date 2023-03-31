package chess.domain.model.piece.shape.strategy.king;

import chess.domain.model.player.Color;
import chess.domain.model.position.Position;
import chess.domain.model.piece.shape.strategy.PieceStrategy;

public class KingStrategy implements PieceStrategy {

    @Override
    public void validateDirection(final Position source, final Position target, final Color sourceColor,
                                  final boolean doesTargetPositionHavePiece) {
        int fileDistance = Math.abs(source.getFileValue() - target.getFileValue());
        int rankDistance = Math.abs(source.getRankValue() - target.getRankValue());

        if (fileDistance > 1 || rankDistance > 1) {
            throw new IllegalArgumentException("킹은 한 칸 이상 이동할 수 없습니다.");
        }
    }

}
