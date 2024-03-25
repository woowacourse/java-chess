package chess.domain.square;

import chess.domain.position.PathFinder;
import chess.domain.position.Position;

import chess.domain.square.piece.Color;
import java.util.Map;

public class Empty implements Square {
    private static final Empty INSTANCE = new Empty();

    private Empty() {
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean canArrive(PathFinder pathFinder, Map<Position, Square> board) {
        return false;
    }

    @Override
    public boolean isColor(final Color color) {
        return false;
    }
}
