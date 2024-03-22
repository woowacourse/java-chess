package chess.domain.piece;

import static chess.domain.attribute.File.D;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public class Queen extends SlidingPiece {

    private static final Position WHITE_INITIAL_POSITION = Position.of(D, ONE);
    private static final Position BLACK_INITIAL_POSITION = Position.of(D, EIGHT);

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    public static Piece ofInitialPosition(final Color color) {
        if (color.isBlack()) {
            return new Queen(color, BLACK_INITIAL_POSITION);
        }
        return new Queen(color, WHITE_INITIAL_POSITION);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
