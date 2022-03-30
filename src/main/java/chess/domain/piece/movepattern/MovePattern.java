package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public interface MovePattern {

    boolean canMove(Position source, Position destination);

    Direction findDirection(Position source, Position destination);

    List<Direction> getDirections();
}
