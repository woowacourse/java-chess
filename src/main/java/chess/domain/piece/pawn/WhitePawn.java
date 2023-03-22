package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Color;

import java.util.List;

public final class WhitePawn extends Pawn {

    private WhitePawn(final Color color, final MovingStrategies movingStrategies) {
        super(color, movingStrategies);
    }

    public static WhitePawn create() {
        final List<MovingStrategy> movingStrategies = List.of(
                new MoveUp(), new MoveLeftUp(), new MoveRightUp());
        return new WhitePawn(Color.WHITE, new MovingStrategies(movingStrategies));
    }
}
