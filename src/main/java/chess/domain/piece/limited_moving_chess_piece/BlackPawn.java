package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public class BlackPawn extends Pawn {

    public BlackPawn() {
        super(List.of(Movement.DOWN_DOWN, Movement.DOWN, Movement.LEFT_DOWN, Movement.RIGHT_DOWN), Color.BLACK);
    }
}
