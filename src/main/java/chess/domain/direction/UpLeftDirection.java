package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public class UpLeftDirection implements Direction {

    @Override
    public List<Position> move(final Position from) {
        if (!from.isMaximumRow() && !from.isMinimumColumn()) {
            return List.of(new Position(from.row() + 1, from.column() - 1));
        }
        return List.of();
    }
}
