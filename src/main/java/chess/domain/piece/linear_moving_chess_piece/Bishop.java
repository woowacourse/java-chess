package chess.domain.piece.linear_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class Bishop extends LinearMovingChessPiece {

    private static final List<Movement> DIRECTIONS = List.of(Movement.LEFT_DOWN, Movement.LEFT_UP, Movement.RIGHT_DOWN, Movement.RIGHT_UP);

    public Bishop(Color side) {
        super(DIRECTIONS, side);
    }

    @Override
    public String name() {
        return "B";
    }
}
