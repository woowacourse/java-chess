package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class WhitePawn extends Pawn {

    private static final List<Movement> ROUTES = List.of(Movement.UP_UP, Movement.UP, Movement.LEFT_UP, Movement.RIGHT_UP);

    public WhitePawn() {
        super(ROUTES, Color.WHITE);
    }
}
