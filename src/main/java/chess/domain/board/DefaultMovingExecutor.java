package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.route.Route;

import java.util.Map;

public class DefaultMovingExecutor extends AbstractMovingExecutor {
    public DefaultMovingExecutor(Piece piece) {
        super(piece);
    }

    @Override
    public void move(Route route, Map<Position, Piece> board, Position fromPosition, Position toPosition) {
        checkIfDisturbed(route, board);

        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    private void checkIfDisturbed(Route route, Map<Position, Piece> board) {
        for (Position position : route.getRoute()) {
            if (board.get(position) != null) {
                throw new IllegalArgumentException("선택한 기물이 움직일 수 없는 위치입니다.");
            }
        }
    }
}
