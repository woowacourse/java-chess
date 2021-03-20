package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class King extends AbstractPiece {

    private static final String SYMBOL = "k";
    private static final int ABLE_LENGTH = 1;

    public static final double SCORE = 0;

    public King(Color color, Position position) {
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
    public Piece move(Position position, Map<Position, Piece> pieces) {
        final List<Direction> directions = Direction.everyDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);

        validateObstacle(position, direction, pieces);
        return new King(color, position);
    }
}
