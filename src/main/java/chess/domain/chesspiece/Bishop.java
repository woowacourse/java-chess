package chess.domain.chesspiece;

import java.util.Arrays;

import static chess.domain.chesspiece.Direction.*;

public class Bishop extends ChessPiece {
    public Bishop(Team team) {
        super("b", team, 3, Arrays.asList(
                LEFT_DOWN,
                LEFT_UP,
                RIGHT_DOWN,
                RIGHT_UP
        ));
    }
}
