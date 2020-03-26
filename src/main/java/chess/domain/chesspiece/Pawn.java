package chess.domain.chesspiece;

import chess.domain.Team;

import java.util.Arrays;
import java.util.Collections;

import static chess.domain.Direction.*;
import static chess.domain.Direction.RIGHT_UP;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Team team) {
        super("p", team, 1, Arrays.asList(
                UP,
                LEFT_UP,
                RIGHT_UP
        ));
    }



    public boolean isFirstMove(){
        if(isFirstMove == true){
            isFirstMove = false;
            return true;
        }
        return false;
    }
}
