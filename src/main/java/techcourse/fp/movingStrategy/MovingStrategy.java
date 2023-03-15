package techcourse.fp.movingStrategy;

import techcourse.fp.chess.domain.Position;

public interface MovingStrategy {

    boolean movable(Position source, Position target);

    Position move(Position currentPosition);
}
