package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OutputView {

    public static void printInitialBoard(final Board board) {
        int i =0;
        for(Square square : board.getSquares().values()) {
            System.out.print(square);
            if(i++ >6){
                i =0;
                System.out.println();
            }
        }
    }
}
