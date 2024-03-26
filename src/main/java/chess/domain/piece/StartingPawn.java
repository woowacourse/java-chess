package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.DOWN;
import static chess.domain.chessboard.attribute.Direction.UP;

import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class StartingPawn extends AbstractPawn {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Positions.of(
            "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"
    );
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Positions.of(
            "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"
    );

    private static final Set<Movement> WHITE_POSSIBLE_MOVEMENTS = Set.of(
            Movement.of(UP, UP),
            Movement.of(UP)
    );

    private static final Set<Movement> BLACK_POSSIBLE_MOVEMENTS = Set.of(
            Movement.of(DOWN, DOWN),
            Movement.of(DOWN)
    );

    public StartingPawn(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<StartingPawn> ofInitialPositions(final Color color) {
        return initialPiecesOf(
                selectByColor(color, WHITE_INITIAL_POSITIONS, BLACK_INITIAL_POSITIONS),
                color,
                StartingPawn::new
        );
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        validateTarget(movablePositions(chessboard), target);
        return new Pawn(color(), target);
    }

    @Override
    public Set<Position> movablePositions(final Chessboard chessboard) {
        return movablePositions(chessboard, selectByColor(color(), WHITE_POSSIBLE_MOVEMENTS, BLACK_POSSIBLE_MOVEMENTS));
    }
}
