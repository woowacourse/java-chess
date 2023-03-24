package chess.domain.piece.pawn;

import chess.domain.game.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Collections;
import java.util.List;

public abstract class Pawn extends Piece {

    protected Pawn(final Color color, final MovingStrategies movingStrategies) {
        super(color, PieceType.PAWN, movingStrategies);
    }

    @Override
    public List<Position> calculatePath(final MovingStrategy strategy, final Position source, final Position target, final Color targetColor) {
        final MovingStrategy movingStrategy = movingStrategies.findStrategy(source, target);
        final Position movedPosition = movingStrategy.move(source);
        if (movingStrategy.isAttackStrategy()) {
            if (movedPosition.equals(target) && color.isOpponent(targetColor)) {
                return Collections.emptyList();
            }
            throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
        }
        if (movedPosition.equals(target) && targetColor.isEmpty()) {
            return Collections.emptyList();
        }
        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }
}
