package chess.domain.chesspiece;

import java.util.Arrays;

import static chess.domain.chesspiece.Direction.*;

public class Queen extends ChessPiece {
    public Queen(Team team) {
        super("q", team, 9, Arrays.asList(
                UP,
                DOWN,
                LEFT,
                RIGHT,
                LEFT_DOWN,
                LEFT_UP,
                RIGHT_DOWN,
                RIGHT_UP
        ));
    }
}
