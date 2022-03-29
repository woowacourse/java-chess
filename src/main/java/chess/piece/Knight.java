package chess.piece;

import static chess.game.Direction.*;

import chess.game.Direction;
import chess.game.Position;
import java.util.List;

public class Knight extends AbstractPiece {

    private static final List<Direction> directions = List.of(NNE, ENE, ESE, SSE, SSW, WSW, WNW, NNW);

    private static final double SCORE = 2.5;

    public Knight(final Color color) {
        super(Name.KNIGHT, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return directions.stream()
                .anyMatch(direction -> canMove(from, to, direction));
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    private boolean canMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return direction.isEqualTo(columnDistance, rowDistance);
    }
}
