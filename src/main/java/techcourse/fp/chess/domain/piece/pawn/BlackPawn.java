package techcourse.fp.chess.domain.piece.pawn;

import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;

public class BlackPawn extends Pawn {

    private static final int INITIAL_BLACK_RANK = 7;

    public BlackPawn(final Color color, final MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static BlackPawn create() {
        return new BlackPawn(Color.BLACK, new SlidingStrategy(List.of(Direction.DOWN)));
    }

    @Override
    protected boolean isStartPosition(final Position source) {
        return source.getRankOrder() == INITIAL_BLACK_RANK;
    }
}
