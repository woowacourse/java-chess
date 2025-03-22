package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public class King extends LimitedMovingChessPiece {

    public King() {
        super(List.of(
                Movement.LEFT, Movement.LEFT_DOWN, Movement.UP, Movement.RIGHT,
                Movement.LEFT_DOWN, Movement.LEFT_UP, Movement.RIGHT_DOWN, Movement.RIGHT_UP
        ));
    }

    @Override
    public String name() {
        return "K";
    }
}
