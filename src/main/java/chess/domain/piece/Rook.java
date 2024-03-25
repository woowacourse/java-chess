package chess.domain.piece;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;

import java.util.Set;

import chess.domain.chessboard.attribute.Direction;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class Rook extends SlidingPiece {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Positions.of("a1", "h1");
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Positions.of("a8", "h8");

    private static final Set<Direction> POSSIBLE_DIRECTIONS = Direction.orthogonal();

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
    public Piece move(final Position target) {
        validateTarget(possiblePositionsTo(POSSIBLE_DIRECTIONS), target);
        return new Rook(color(), target);
    }
}
