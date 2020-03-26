package chess.domain.chesspiece;

import chess.domain.Team;

import java.util.Collections;

public class Knight extends ChessPiece {
    public Knight(Team team) {
        super("n", team, 2.5, Collections.EMPTY_LIST);
    }
}
