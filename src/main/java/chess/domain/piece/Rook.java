package chess.domain.piece;

import java.util.Set;

import chess.domain.chessboard.Chessboard;
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
        return initialPiecesOf(
                initialPositionsBy(color, WHITE_INITIAL_POSITIONS, BLACK_INITIAL_POSITIONS),
                color,
                Rook::new
        );
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        validateTarget(movablePositions(chessboard), target);
        return new Rook(color(), target);
    }

    @Override
    public Set<Position> movablePositions(final Chessboard chessboard) {
        return movablePositions(chessboard, POSSIBLE_DIRECTIONS);
    }
}
