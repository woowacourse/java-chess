package chess.domain.chesspiece;

import java.util.Arrays;

import static chess.domain.chesspiece.Direction.*;

public class Rook extends ChessPiece {
    public Rook(Team team) {
        super("r", team, 5, Arrays.asList(
                UP,
                DOWN,
                LEFT,
                RIGHT
        ));
    }
}
