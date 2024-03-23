package chess.domain.piece;

import java.util.List;

public class Bishop extends AbstractSlidingPiece {

    public Bishop(Team team) {
        super(PieceType.BISHOP, team, List.of(
                Direction.LEFT_DOWN,
                Direction.LEFT_UP,
                Direction.RIGHT_DOWN,
                Direction.RIGHT_UP
        ));
    }
}
