package chess.model.piece;

import chess.model.position.Position;
import java.util.Map;

public interface MoveStrategy {

    void move(Position source, Position target, Map<Position, Piece> pieces);
}
