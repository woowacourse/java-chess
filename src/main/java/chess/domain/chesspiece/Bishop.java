package chess.domain.chesspiece;

import chess.domain.team.Team;

import java.util.Arrays;

import static chess.domain.board.Direction.*;

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
