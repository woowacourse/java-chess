package chess.domain.piece.movingstrategy;

import chess.domain.position.directionstrategy.HorizontalDirection;
import chess.domain.position.directionstrategy.VerticalDirection;
import java.util.Set;

public class RookMovingStrategy extends MovingStrategy {

    public RookMovingStrategy() {
        super(Set.of(new VerticalDirection(), new HorizontalDirection()));
    }
}
