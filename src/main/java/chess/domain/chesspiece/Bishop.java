package chess.domain.chesspiece;

import chess.domain.Team;

import java.util.Arrays;

import static chess.domain.Direction.*;

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
