package chess.domain.strategy.queen;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.strategy.PieceStrategy;
import chess.domain.strategy.bishop.BishopStrategy;
import chess.domain.strategy.rook.RookStrategy;


public class QueenStrategy implements PieceStrategy {

    private final RookStrategy rookStrategy = new RookStrategy();
    private final BishopStrategy bishopStrategy = new BishopStrategy();

    @Override
    public void validateDirection(final Position source, final Position target, final Color sourceColor,
                                  final boolean doesTargetPositionHavePiece) {
        try {
            rookStrategy.validateDirection(source, target, sourceColor, doesTargetPositionHavePiece);
        } catch (IllegalArgumentException ignored) {
            validateBishopStrategy(source, target, sourceColor, doesTargetPositionHavePiece);
        }
    }

    private void validateBishopStrategy(final Position source, final Position target, final Color sourceColor,
                                        final boolean doesTargetPositionHavePiece) {
        try {
            bishopStrategy.validateDirection(source, target, sourceColor, doesTargetPositionHavePiece);
        } catch (IllegalArgumentException queen) {
            throw new IllegalArgumentException("퀸의 이동 경로가 아닙니다.");
        }
    }

}
