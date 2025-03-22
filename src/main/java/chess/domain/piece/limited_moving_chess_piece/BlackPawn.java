package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class BlackPawn extends Pawn {

    private static final List<Movement> ROUTES = List.of(Movement.DOWN_DOWN, Movement.DOWN, Movement.LEFT_DOWN, Movement.RIGHT_DOWN);

    public BlackPawn() {
        super(ROUTES, Color.BLACK);
    }
}
