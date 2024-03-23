package chess.domain.piece;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;

import java.util.Set;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class Bishop extends SlidingPiece {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Positions.of("c1", "f1");
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Positions.of("c8", "f8");

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Bishop> ofInitialPositions(final Color color) {
        if (color.isBlack()) {
            return initialPiecesOf(BLACK_INITIAL_POSITIONS, BLACK, Bishop::new);
        }
        return initialPiecesOf(WHITE_INITIAL_POSITIONS, WHITE, Bishop::new);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
