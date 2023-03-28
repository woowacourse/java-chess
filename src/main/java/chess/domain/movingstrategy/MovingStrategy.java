package chess.domain.movingstrategy;

import chess.domain.game.Position;

public interface MovingStrategy {

    boolean movable(Position source, Position target);

    Position move(Position currentPosition);
}
