package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(List.of(Movement.UP_UP, Movement.UP, Movement.LEFT_UP, Movement.RIGHT_UP), Color.WHITE);
    }
}
