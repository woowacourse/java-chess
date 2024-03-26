package chess.domain.piece;

import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Direction;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class Bishop extends SlidingPiece {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Positions.of("c1", "f1");
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Positions.of("c8", "f8");

    private static final Set<Direction> POSSIBLE_DIRECTIONS = Direction.diagonal();

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Bishop> ofInitialPositions(final Color color) {
        return initialPiecesOf(
                selectByColor(color, WHITE_INITIAL_POSITIONS, BLACK_INITIAL_POSITIONS),
                color,
                Bishop::new
        );
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        validateTarget(movablePositions(chessboard), target);
        return new Bishop(color(), target);
    }

    @Override
    public Set<Position> movablePositions(final Chessboard chessboard) {
        return movablePositions(chessboard, POSSIBLE_DIRECTIONS);
    }
}
