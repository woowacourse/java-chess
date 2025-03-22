package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public class Bishop extends LinearMovingChessPiece {

    public Bishop() {
        super(List.of(
                Movement.LEFT_DOWN, Movement.LEFT_UP, Movement.RIGHT_DOWN, Movement.RIGHT_UP
        ));
    }
}
