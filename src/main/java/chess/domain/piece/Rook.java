package chess.domain.piece;

import java.util.List;
import java.util.Map;

public final class Rook extends AbstractPiece {

    private static final double SCORE = 5;
    private static final String SYMBOL = "r";
    private static final int ABLE_LENGTH = 7;

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public String symbol() {
        return changeColorSymbol(SYMBOL);
    }

    @Override
    public Piece move(final Position position, final Map<Position, Piece> pieces) {
        final List<Direction> directions = Direction.linearDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);

        validateObstacle(position, direction, pieces);
        return new Rook(color, position);
    }

    @Override
    public double score() {
        return SCORE;
    }
}
