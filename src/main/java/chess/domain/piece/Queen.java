package chess.domain.piece;

import java.util.List;

public class Queen extends AbstractSlidingPiece {

    public Queen(Team team) {
        super(PieceType.QUEEN, team, List.of(
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
