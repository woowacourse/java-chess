package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class Queen extends LinearMovingChessPiece {

    public Queen(Color side) {
        super(List.of(
                Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT,
                Movement.RIGHT_DOWN, Movement.LEFT_DOWN, Movement.RIGHT_UP, Movement.LEFT_UP)
                , side);
    }

    @Override
    public String name() {
        return "Q";
    }
}
