package chess.domain.piece.movingstrategy;

import chess.domain.position.directionstrategy.DiagonalDirection;
import java.util.Set;

public class BishopMovingStrategy extends MovingStrategy {

    public BishopMovingStrategy() {
        super(Set.of(new DiagonalDirection()));
    }
}
