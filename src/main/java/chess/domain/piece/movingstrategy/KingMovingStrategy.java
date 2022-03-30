package chess.domain.piece.movingstrategy;

import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import chess.domain.position.directionstrategy.DiagonalDirection;
import chess.domain.position.directionstrategy.HorizontalDirection;
import chess.domain.position.directionstrategy.VerticalDirection;
import java.util.Set;

public class KingMovingStrategy extends MovingStrategy {
    public KingMovingStrategy() {
        super(Set.of(new VerticalDirection(), new HorizontalDirection(), new DiagonalDirection()));
    }

    @Override
    public boolean isAbleToMove(Position from, Position to, PieceColor pieceColor) {
        if (from.isFartherThanOneStep(to)) {
            return false;
        }

        return super.isAbleToMove(from, to, pieceColor);
    }
}
