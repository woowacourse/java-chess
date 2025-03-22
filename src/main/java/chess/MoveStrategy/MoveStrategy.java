package chess.MoveStrategy;

import chess.domain.Movement;
import chess.domain.piece.Position;
import java.util.List;

public interface MoveStrategy {

    List<Position> canMove(Position startPosition, Position endPosition, List<Movement> canMove,
                           final boolean firstMove);
}
