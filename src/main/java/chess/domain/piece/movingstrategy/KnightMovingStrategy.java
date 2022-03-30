package chess.domain.piece.movingstrategy;

import chess.domain.position.directionstrategy.SevenShapeDirection;
import java.util.Set;

public class KnightMovingStrategy extends MovingStrategy {

    public KnightMovingStrategy() {
        super(Set.of(new SevenShapeDirection()));
    }
}
