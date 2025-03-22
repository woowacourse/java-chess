package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public class Rook extends LinearMovingChessPiece {

    public Rook() {
        super(List.of(
                Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT
        ));
    }
}
