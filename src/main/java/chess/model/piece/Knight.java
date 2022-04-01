package chess.model.piece;

import static chess.model.strategy.move.Direction.EEN;
import static chess.model.strategy.move.Direction.EES;
import static chess.model.strategy.move.Direction.NNE;
import static chess.model.strategy.move.Direction.NNW;
import static chess.model.strategy.move.Direction.SSE;
import static chess.model.strategy.move.Direction.SSW;
import static chess.model.strategy.move.Direction.WWN;
import static chess.model.strategy.move.Direction.WWS;

import chess.model.Color;
import chess.model.strategy.LimitedMovableStrategy;
import chess.model.strategy.move.Direction;
import java.util.List;

public final class Knight extends Piece {

    private static final List<Direction> KNIGHT_DIRECTION = List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    private static final int KNIGHT_MOVE_LIMIT = 1;

    public Knight(Color color) {
        super(color, new LimitedMovableStrategy(KNIGHT_DIRECTION, KNIGHT_MOVE_LIMIT));
    }

    @Override
    public double getPointValue() {
        return Point.KNIGHT.getValue();
    }
}
