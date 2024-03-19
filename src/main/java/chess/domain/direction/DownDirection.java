package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public class DownDirection implements Direction {

    @Override
    public List<Position> move(final Position from) {
        if (!from.isMinimumRow()) {
            return List.of(new Position(from.row() - 1, from.column()));
        }

        return List.of();

    }
}
