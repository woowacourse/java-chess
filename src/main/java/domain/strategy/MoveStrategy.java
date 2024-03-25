package domain.strategy;

import domain.board.Position;
import domain.piece.Piece;
import domain.piece.info.Direction;
import java.util.List;
import java.util.Map;

public interface MoveStrategy {
    List<Position> movablePositions(final Position source, final List<Direction> directions,
                                    final Map<Position, Piece> pieces);
}
