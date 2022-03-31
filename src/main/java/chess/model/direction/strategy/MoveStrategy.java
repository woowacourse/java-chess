package chess.model.direction.strategy;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public interface MoveStrategy {

    List<Position> searchMovablePositions(Position source, Map<Position, Piece> board);
}
