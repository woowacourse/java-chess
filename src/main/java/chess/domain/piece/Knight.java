package chess.domain.piece;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public class Knight extends UnslidingPiece {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Set.of(
            Position.of(B, ONE), Position.of(G, ONE)
    );
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Set.of(
            Position.of(B, EIGHT), Position.of(G, EIGHT)
    );

    public Knight(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Piece> ofInitialPositions(final Color color) {
        if (color.isBlack()) {
            return initialPiecesOf(BLACK_INITIAL_POSITIONS, BLACK, Knight::new);
        }
        return initialPiecesOf(WHITE_INITIAL_POSITIONS, WHITE, Knight::new);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
