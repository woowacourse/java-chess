package chess.domain.chesspieceMove;

import chess.domain.Position;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class KingMove implements Move{
    private static final int MAX_DISTANCE = 2;

    private static KingMove kingMove;

    private KingMove() {}

    public static KingMove getInstance() {
        if (Objects.isNull(kingMove))
            return new KingMove();

        return kingMove;
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
