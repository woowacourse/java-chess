package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.DOWN;
import static chess.domain.chessboard.attribute.Direction.DOWN_LEFT;
import static chess.domain.chessboard.attribute.Direction.DOWN_RIGHT;
import static chess.domain.chessboard.attribute.Direction.LEFT;
import static chess.domain.chessboard.attribute.Direction.RIGHT;
import static chess.domain.chessboard.attribute.Direction.UP;
import static chess.domain.chessboard.attribute.Direction.UP_LEFT;
import static chess.domain.chessboard.attribute.Direction.UP_RIGHT;

import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class Knight extends UnslidingPiece {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Positions.of("b1", "g1");
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Positions.of("b8", "g8");

    private static final Set<Movement> POSSIBLE_MOVEMENTS = Set.of(
            Movement.of(UP, UP_LEFT), Movement.of(UP, UP_RIGHT),
            Movement.of(RIGHT, UP_RIGHT), Movement.of(RIGHT, DOWN_RIGHT),
            Movement.of(DOWN, DOWN_LEFT), Movement.of(DOWN, DOWN_RIGHT),
            Movement.of(LEFT, DOWN_LEFT), Movement.of(LEFT, UP_LEFT)
    );

    public Knight(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Knight> ofInitialPositions(final Color color) {
        return initialPiecesOf(
                selectByColor(color, WHITE_INITIAL_POSITIONS, BLACK_INITIAL_POSITIONS),
                color,
                Knight::new
        );
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        validateTarget(movablePositions(chessboard), target);
        return new Knight(color(), target);
    }

    @Override
    public Set<Position> movablePositions(final Chessboard chessboard) {
        return movablePositions(chessboard, POSSIBLE_MOVEMENTS);
    }
}
