package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public class Knight extends LimitedMovingChessPiece {

    public Knight() {
        super(List.of(
                Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT,
                Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_RIGHT_UP,
                Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT,
                Movement.LEFT_LEFT_DOWN, Movement.LEFT_LEFT_UP
        ));
    }

    @Override
    public String name() {
        return "N";
    }
}
