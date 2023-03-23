package techcourse.fp.chess.domain.piece;

import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;

public class WhitePawn extends Pawn {

    private static final int INITIAL_WHITE_RANK = 2;

    public WhitePawn(final Color color, final MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static WhitePawn create() {
        return new WhitePawn(Color.WHITE, new SlidingStrategy(List.of(Direction.UP)));
    }

    @Override
    protected boolean isStartPosition(final Position source) {
        return source.getRankOrder() == INITIAL_WHITE_RANK;
    }
}
