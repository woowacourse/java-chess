package chess.domain.board;

import chess.domain.piece.Placeable;
import chess.domain.position.Position;
import chess.domain.route.Route;

import java.util.Map;

public interface MovingExecutor {
    void move(Route route, Map<Position, Placeable> board, Position fromPosition, Position toPosition);
}
