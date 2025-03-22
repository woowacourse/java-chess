package chess.domain.piece.linear_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class Rook extends LinearMovingChessPiece {

    private static final List<Movement> DIRECTIONS = List.of(
            Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT
    );

    public Rook(Color side) {
        super(DIRECTIONS, side);
    }

    @Override
    public String name() {
        return "R";
    }
}
