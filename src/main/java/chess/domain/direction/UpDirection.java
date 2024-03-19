package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public class UpDirection implements Direction {

    @Override
    public List<Position> move(final Position from) {
        if (!from.isMaximumRow()) {
            return List.of(new Position(from.row() + 1, from.column()));
        }
        return List.of();
    }
}
