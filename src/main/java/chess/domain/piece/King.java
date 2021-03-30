package chess.domain.piece;

import java.util.List;
import java.util.Map;

public final class King extends AbstractPiece {

    private static final double SCORE = 0;
    private static final String SYMBOL = "k";
    private static final int ABLE_LENGTH = 1;

    public King(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public String symbol() {
        return changeColorSymbol(SYMBOL);
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public Piece move(final Position position, final Map<Position, Piece> pieces) {
        final List<Direction> directions = Direction.everyDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);

        validateObstacle(position, direction, pieces);
        return new King(color, position);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public List<Position> movablePositions(Map<Position, Piece> pieces) {
        final List<Direction> directions = Direction.everyDirection();

        return positions(pieces, directions, ABLE_LENGTH);
    }
}
