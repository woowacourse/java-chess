package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Placeable;
import chess.domain.position.Position;
import chess.domain.route.Route;

import java.util.Map;

public class KnightMovingExecutor extends AbstractMovingExecutor {
    public KnightMovingExecutor(Placeable placeable) {
        super(placeable);
    }

    @Override
    public void move(Route route, Map<Position, Placeable> board, Position fromPosition, Position toPosition) {
        board.put(fromPosition, new Empty());
        board.put(toPosition, placeable);
    }
}
