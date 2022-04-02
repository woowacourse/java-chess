package chess.model.strategy;

import chess.model.MoveType;
import chess.model.position.Position;

public class NotMoveStrategy implements MoveStrategy {
    @Override
    public boolean movable(Position source, Position target, MoveType moveType) {
        return false;
    }
}
