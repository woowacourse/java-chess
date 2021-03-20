package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class Bishop extends AbstractPiece {

    private static final double SCORE = 3;
    private static final String SYMBOL = "b";
    private static final int ABLE_LENGTH = 7;

    public Bishop(Color color, Position position) {
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
        final List<Direction> directions = Direction.diagonalDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);

        validateObstacle(position, direction, pieces);
        return new Rook(color, position);
    }
}
