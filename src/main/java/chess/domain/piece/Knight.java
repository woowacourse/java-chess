package chess.domain.piece;

import java.util.List;

public class Knight extends AbstractNonSlidingPiece {

    public Knight(Team team) {
        super(PieceType.KNIGHT, team, List.of(
                Direction.UP_UP_LEFT,
                Direction.UP_UP_RIGHT,
                Direction.DOWN_DOWN_LEFT,
                Direction.DOWN_DOWN_RIGHT,
                Direction.LEFT_LEFT_UP,
                Direction.LEFT_LEFT_DOWN,
                Direction.RIGHT_RIGHT_UP,
                Direction.RIGHT_RIGHT_DOWN
        ));
    }
}
