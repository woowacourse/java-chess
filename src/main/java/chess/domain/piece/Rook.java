package chess.domain.piece;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;
import static chess.domain.chessboard.attribute.File.A;
import static chess.domain.chessboard.attribute.File.H;
import static chess.domain.chessboard.attribute.Rank.EIGHT;
import static chess.domain.chessboard.attribute.Rank.ONE;

import java.util.Set;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class Rook extends SlidingPiece {

    protected static final Set<Position> WHITE_INITIAL_POSITIONS = Positions.of("a1", "h1");
    protected static final Set<Position> BLACK_INITIAL_POSITIONS = Positions.of("a8", "h8");

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Rook> ofInitialPositions(final Color color) {
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
