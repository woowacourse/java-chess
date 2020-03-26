package chess.domain.chesspiece;

import chess.domain.team.Team;

import java.util.Arrays;

import static chess.domain.board.Direction.*;

public class King extends ChessPiece {
    public King(Team team) {
        super("k", team, 0, Arrays.asList(
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
