package chess.domain.piece;

import chess.domain.location.Location;

public interface Movable {

    boolean isMovable(final Location target);
}
