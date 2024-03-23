package chess.domain.piece;

import java.util.List;

public class Rook extends AbstractSlidingPiece {

    public Rook(Team team) {
        super(PieceType.ROOK, team, List.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT
        ));
    }
}
