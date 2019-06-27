package chess.domain.chessmove;

import chess.domain.Position;

import java.util.Collections;
import java.util.List;

public class KingMove implements Move {
    private static final int MAX_DISTANCE = 2;

    private static class KingMoveLazyHolder {
        private static final KingMove INSTANCE = new KingMove();
    }

    public static KingMove getInstance() {
        return KingMoveLazyHolder.INSTANCE;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }

        return Collections.singletonList(target);
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        return !source.equals(target)
                && source.getDistance(target) < MAX_DISTANCE;
    }
}