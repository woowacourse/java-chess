package techcourse.fp.chess.movingStrategy;

import techcourse.fp.chess.domain.Position;

public interface MovingStrategy {

    boolean movable(Position source, Position target);

    Position move(Position currentPosition);
}
