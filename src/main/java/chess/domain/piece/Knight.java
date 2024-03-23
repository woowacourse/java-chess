package chess.domain.piece;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;

import java.util.Set;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public class Knight extends UnslidingPiece {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Set.of(
            Position.from("b1"), Position.from("g1")
    );
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Set.of(
            Position.from("b8"), Position.from("g8")
    );

    public Knight(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Knight> ofInitialPositions(final Color color) {
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
