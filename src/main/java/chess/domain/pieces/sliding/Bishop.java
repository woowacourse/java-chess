package chess.domain.pieces.sliding;

import chess.domain.Team;
import chess.domain.math.Direction;

public final class Bishop extends SlidingPiece {

    public Bishop(final Team team) {
        super(team, Direction.ofDiagonal());
    }
}
