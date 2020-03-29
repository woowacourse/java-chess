package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Placeable;
import chess.domain.position.Position;
import chess.domain.route.Route;

import java.util.Map;

public class DefaultMovingExecutor extends AbstractMovingExecutor {
    public DefaultMovingExecutor(Placeable placeable) {
        super(placeable);
    }

    @Override
    public void move(Route route, Map<Position, Placeable> board, Position fromPosition, Position toPosition) {
        checkIfDisturbed(route, board);

        board.put(fromPosition, new Empty());
        board.put(toPosition, placeable);
    }

    private void checkIfDisturbed(Route route, Map<Position, Placeable> board) {
        for (Position position : route.getRoute()) {
            if (board.get(position).isNotEmpty()) {
                throw new IllegalArgumentException("선택한 기물이 움직일 수 없는 위치입니다.");
            }
        }
    }
}
