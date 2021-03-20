package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class Knight extends AbstractPiece {

    private static final String SYMBOL = "n";
    private static final int ABLE_LENGTH = 1;

    public static final double SCORE = 2.5;

    public Knight(Color color, Position position) {
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
        final List<Direction> directions = Direction.knightDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);

        validateObstacle(position, direction, pieces);
        return new Knight(color, position);
    }
}
