package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Points;

public interface Piece {

    boolean isMovable(Points points, boolean hasBlock, boolean hasTarget);

    Team team();

    String failMoveMsg();
}
