package techcourse.fp.chess.domain.movingStrategy;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Directions;
import techcourse.fp.chess.domain.Position;

public class NoneSlidingStrategy extends MovingStrategy {

    public NoneSlidingStrategy(final Directions directions) {
        super(directions);
    }

    @Override
    public List<Position> createPath(final Position source, final Position target) {

        if (directions.hasMovableDirection(source, target)) {
            return Collections.emptyList();
        }

        throw new IllegalArgumentException("행마법 상 이동할 수 없는 위치입니다.");
    }
}
