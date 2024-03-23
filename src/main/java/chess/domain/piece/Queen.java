package chess.domain.piece;

import java.util.List;

public class Queen extends AbstractSlidingPiece {

    public Queen(Team team) {
        super(PieceType.QUEEN, team, List.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT,
                Direction.LEFT_UP,
                Direction.LEFT_DOWN,
                Direction.RIGHT_UP,
                Direction.RIGHT_DOWN
        ));
    }
}
