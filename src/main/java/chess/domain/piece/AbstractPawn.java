package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.UP_LEFT;
import static chess.domain.chessboard.attribute.Direction.UP_RIGHT;

import java.util.Set;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public abstract class AbstractPawn extends UnslidingPiece {

    protected static final Set<Movement> POSSIBLE_ATTACKS = Set.of(
            Movement.of(UP_LEFT),
            Movement.of(UP_RIGHT)
    );

    protected AbstractPawn(final Color color, final Position position) {
        super(color, position);
    }
}
