package chess.domain.piece;

import static chess.domain.chessboard.attribute.File.E;
import static chess.domain.chessboard.attribute.Rank.EIGHT;
import static chess.domain.chessboard.attribute.Rank.ONE;

import java.util.Set;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public class King extends UnslidingPiece {

    private static final Position WHITE_INITIAL_POSITION = Position.of(E, ONE);
    private static final Position BLACK_INITIAL_POSITION = Position.of(E, EIGHT);

    public King(final Color color, final Position position) {
        super(color, position);
    }

    public static Piece ofInitialPosition(final Color color) {
        if (color.isBlack()) {
            return new King(color, BLACK_INITIAL_POSITION);
        }
        return new King(color, WHITE_INITIAL_POSITION);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
