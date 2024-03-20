package chess.domain.square;

import chess.domain.position.Path;
import chess.domain.position.Position;

import java.util.Map;

public class Empty implements Square {
    private static final Empty INSTANCE = new Empty();

    private Empty() {
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean canMove(Path path, Map<Position, Square> board) {
        return false;
    }
}
