package chess.piece;

import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public interface Moving {
    public boolean checkCanMove(Map<Position, Piece> board, Position position, Position positionToMove, List<List<Movement>> movementss);
}
