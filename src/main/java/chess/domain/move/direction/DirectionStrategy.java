package chess.domain.move.direction;

import chess.domain.position.Position;

import java.util.List;

public interface DirectionStrategy {
    List<Position> find(Position source, Position target);
}