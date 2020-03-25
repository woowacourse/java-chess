package chess.domain.piece;

import java.util.List;

import chess.domain.board.Position;

public interface MoveStrategy {

    List<Position> findMovePath(Position source, Position target);
}
