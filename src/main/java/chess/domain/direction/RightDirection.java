package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public class RightDirection implements Direction {

    @Override
    public List<Position> move(final Position from) {
        if (!from.isMaximumColumn()) {
            return List.of(new Position(from.row(), from.column() + 1));
        }
        return List.of();
    }
}
