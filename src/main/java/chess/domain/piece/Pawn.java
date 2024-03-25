package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.UP;

import java.util.Set;

import chess.domain.chessboard.attribute.Direction;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public class Pawn extends AbstractPawn {

    private static final Set<Movement> POSSIBLE_MOVEMENTS = Set.of(
            Movement.of(UP),
            Movement.of(Direction.UP_LEFT),
            Movement.of(Direction.UP_RIGHT)
    );

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Piece move(final Position target) {
        validateTarget(possiblePositionsAfter(POSSIBLE_MOVEMENTS), target);
        return new Pawn(color(), target);
    }
}
