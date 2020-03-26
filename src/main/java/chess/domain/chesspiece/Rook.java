package chess.domain.chesspiece;

import chess.domain.team.Team;

import java.util.Arrays;

import static chess.domain.board.Direction.*;

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
