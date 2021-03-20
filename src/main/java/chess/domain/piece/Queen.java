package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class Queen extends AbstractPiece {

    private static final String SYMBOL = "q";

    private static final double SCORE = 9;
    public static final int ABLE_LENGTH = 7;

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String symbol() {
        return changeColorSymbol(SYMBOL);
    }

    @Override
    public Piece move(Position position, Map<Position, Piece> pieces) {
        final List<Direction> directions = Direction.everyDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);

        validateObstacle(position, direction, pieces);
        return new Queen(color, position);
    }

    @Override
    public double score() {
        return SCORE;
    }
}
