package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;

public interface MovePattern {

    boolean canMove(Position source, Position destination);

    Direction findDirection(Position source, Position destination);
}
