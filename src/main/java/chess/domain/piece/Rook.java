package chess.domain.piece;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;

import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public class Rook extends SlidingPiece {

    protected static final Set<Position> WHITE_INITIAL_POSITIONS = Set.of(
            Position.of(A, ONE), Position.of(H, ONE)
    );
    protected static final Set<Position> BLACK_INITIAL_POSITIONS = Set.of(
            Position.of(A, EIGHT), Position.of(H, EIGHT)
    );

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Piece> ofInitialPositions(final Color color) {
        if (color.isBlack()) {
            return initialPiecesOf(BLACK_INITIAL_POSITIONS, BLACK, Rook::new);
        }
        return initialPiecesOf(WHITE_INITIAL_POSITIONS, WHITE, Rook::new);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
