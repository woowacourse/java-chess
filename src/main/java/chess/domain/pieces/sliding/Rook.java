package chess.domain.pieces.sliding;

import chess.domain.Team;
import chess.domain.math.Direction;

public final class Rook extends SlidingPiece {

    public Rook(final Team team) {
        super(team, Direction.ofCross());
    }
}
