package chess.domain.direction;

import chess.domain.position.Position;

public interface Direction {

    boolean isValidDirection(Position from, Position to);

    boolean isDiagonal();
}
