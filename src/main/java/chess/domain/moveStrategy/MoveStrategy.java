package chess.domain.moveStrategy;

import chess.domain.game.Board;
import chess.domain.location.Position;

import java.util.List;

public interface MoveStrategy {
    List<Position> movablePositions(Position from, Board board);
}
