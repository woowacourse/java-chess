package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class Bishop extends LinearMovingChessPiece {

    public Bishop(Color side) {
        super(List.of(
                Movement.LEFT_DOWN, Movement.LEFT_UP, Movement.RIGHT_DOWN, Movement.RIGHT_UP
        ), side);
    }

    @Override
    public String name() {
        return "B";
    }
}
