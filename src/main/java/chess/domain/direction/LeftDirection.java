package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public class LeftDirection implements Direction {

    @Override
    public List<Position> move(final Position from) {
        if (!from.isMinimumColumn()) {
            return List.of(new Position(from.row(), from.column() - 1));
        }
        return List.of();
    }
}
