package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public interface MovePattern {

    boolean canMove(Position src, Position dest);

    Direction findDirection(Position src, Position dest);

    List<Direction> getDirections();
}
