package chess.domain.piece;

import java.util.List;

public class Bishop extends AbstractSlidingPiece {

    public Bishop(Team team) {
        super(PieceType.BISHOP, team, List.of(
                Direction.DOWN_LEFT,
                Direction.UP_LEFT,
                Direction.DOWN_RIGHT,
                Direction.UP_RIGHT
        ));
    }
}
