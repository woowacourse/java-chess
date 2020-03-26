package chess.domain.chesspiece;

import chess.domain.Team;

import java.util.ArrayList;
import java.util.Arrays;

import static chess.domain.Direction.*;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Team team) {
        super("p", team, 1, Arrays.asList(
                UP,
                LEFT_UP,
                RIGHT_UP)
        );
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void firstMoveComplete() {
        isFirstMove = false;
    }
}
