package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Direction;

import java.util.List;

public interface MoveStrategy {
    List<Position> searchMovablePositions(Position target, List<Direction> directions);

    boolean canMove(Position target, Position destination, Board board);
}
