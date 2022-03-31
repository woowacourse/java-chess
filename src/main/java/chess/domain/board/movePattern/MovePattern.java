package chess.domain.board.movePattern;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public interface MovePattern {

    boolean canMove(Position src, Position dest);

    Direction findDirection(Position src, Position dest);

    List<Direction> getDirections();
}
