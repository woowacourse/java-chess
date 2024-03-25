package chess.domain.piece;

import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Direction;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public class Queen extends SlidingPiece {

    private static final Position WHITE_INITIAL_POSITION = Position.from("d1");
    private static final Position BLACK_INITIAL_POSITION = Position.from("d8");

    private static final Set<Direction> POSSIBLE_DIRECTIONS = Direction.all();

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    public static Piece ofInitialPosition(final Color color) {
        if (color.isBlack()) {
            return new Queen(color, BLACK_INITIAL_POSITION);
        }
        return new Queen(color, WHITE_INITIAL_POSITION);
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        validateTarget(movablePositions(chessboard, POSSIBLE_DIRECTIONS), target);
        return new Queen(color(), target);
    }
}
