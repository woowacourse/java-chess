package chess.domain.piece;

import java.util.List;

public class King extends AbstractNonSlidingPiece {

    public King(Team team) {
        super(PieceType.KING, team, List.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT,
                Direction.UP_LEFT,
                Direction.DOWN_LEFT,
                Direction.UP_RIGHT,
                Direction.DOWN_RIGHT
        ));
    }
}
