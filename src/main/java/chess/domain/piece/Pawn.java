package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.DOWN;
import static chess.domain.chessboard.attribute.Direction.UP;

import java.util.HashSet;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public class Pawn extends AbstractPawn {

    private static final Set<Movement> POSSIBLE_MOVEMENTS_WHITE = Set.of(Movement.of(UP));
    private static final Set<Movement> POSSIBLE_MOVEMENTS_BLACK = Set.of(Movement.of(DOWN));

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        Set<Position> positions = new HashSet<>();
        positions.addAll(attackablePositions(chessboard, possibleAttacksBy(color())));
        positions.addAll(movablePositions(chessboard,
                possibleMovementsBy(color(), POSSIBLE_MOVEMENTS_WHITE, POSSIBLE_MOVEMENTS_BLACK)));
        validateTarget(positions, target);
        return new Pawn(color(), target);
    }
}
