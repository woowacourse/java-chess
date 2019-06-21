package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public interface Direction {
    Position simulateUnitMove(Board board, Position position, boolean isReverseDirection);

    int matchMoveCount(int rowDifference, int columnDifference);
}
