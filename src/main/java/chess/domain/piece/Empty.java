package chess.domain.piece;

import chess.domain.game.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public final class Empty extends Piece {

    private static final Empty INSTANCE = new Empty(new MovingStrategies(Collections.emptyList()));

    private Empty(final MovingStrategies strategies) {
        super(Team.EMPTY, PieceType.EMPTY, strategies);
    }

    public static Empty instance() {
        return INSTANCE;
    }

    @Override
    public List<Position> calculatePath(final MovingStrategy strategy, final Position source, final Position target, final Team targetTeam) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
