package chess.model.piece;

import static chess.model.Direction.EEN;
import static chess.model.Direction.EES;
import static chess.model.Direction.NNE;
import static chess.model.Direction.NNW;
import static chess.model.Direction.SSE;
import static chess.model.Direction.SSW;
import static chess.model.Direction.WWN;
import static chess.model.Direction.WWS;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.LimitedMovableStrategy;
import java.util.List;

public final class Knight extends Piece {

    private static final List<Direction> KNIGHT_DIRECTION = List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    private static final int KNIGHT_MOVE_LIMIT = 1;

    public Knight(Color color, Square square) {
        super(color, square, new LimitedMovableStrategy(KNIGHT_DIRECTION, KNIGHT_MOVE_LIMIT));
    }

    @Override
    public Point getPoint() {
        return Point.KNIGHT;
    }
}
