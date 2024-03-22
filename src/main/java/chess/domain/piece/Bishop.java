package chess.domain.piece;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public class Bishop extends SlidingPiece {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Set.of(
            Position.of(C, ONE), Position.of(F, ONE)
    );
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Set.of(
            Position.of(C, EIGHT), Position.of(F, EIGHT)
    );

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Piece> ofInitialPositions(final Color color) {
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
