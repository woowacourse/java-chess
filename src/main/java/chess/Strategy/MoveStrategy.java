package chess.Strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;

import java.util.List;

public interface MoveStrategy {
    List<Position> searchMovablePositions(Position target);

    boolean canMove(Position target, Position destination, Board board);
}
