package chess.domain.pieces.sliding;

import chess.domain.Team;
import chess.domain.math.Direction;

public final class Queen extends SlidingPiece {

    public Queen(final Team team) {
        super(team, Direction.ofAllExpectKnight());
    }
}
