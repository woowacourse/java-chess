package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public class LeftDirection implements Direction {
    
    @Override
    public List<Position> move(final Position from) {
        return List.of(new Position(from.row() - 1, from.column()));
    }
}
