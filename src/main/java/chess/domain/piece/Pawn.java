package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.DOWN;
import static chess.domain.chessboard.attribute.Direction.UP;

import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public class Pawn extends AbstractPawn {

    private static final Set<Movement> WHITE_POSSIBLE_MOVEMENTS = Set.of(Movement.of(UP));
    private static final Set<Movement> BLACK_POSSIBLE_MOVEMENTS = Set.of(Movement.of(DOWN));

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        validateTarget(movablePositions(chessboard), target);
        return new Pawn(color(), target);
    }

    @Override
    public Set<Position> movablePositions(final Chessboard chessboard) {
        return movablePositions(chessboard, WHITE_POSSIBLE_MOVEMENTS, BLACK_POSSIBLE_MOVEMENTS);
    }
}
