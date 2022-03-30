package chess.domain.piece.movingstrategy;

import chess.domain.position.directionstrategy.DiagonalDirection;
import chess.domain.position.directionstrategy.HorizontalDirection;
import chess.domain.position.directionstrategy.VerticalDirection;
import java.util.Set;

public class QueenMovingStrategy extends MovingStrategy {

    public QueenMovingStrategy() {
        super(Set.of(new VerticalDirection(), new HorizontalDirection(), new DiagonalDirection()));
    }
}
