package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class King extends LimitedMovingChessPiece {

    private static final List<Movement> ROUTES = List.of(
            Movement.LEFT, Movement.UP, Movement.RIGHT, Movement.DOWN,
            Movement.LEFT_DOWN, Movement.LEFT_UP, Movement.RIGHT_DOWN, Movement.RIGHT_UP
    );

    public King(Color side) {
        super(ROUTES, side);
    }

    @Override
    public String name() {
        return "K";
    }
}
