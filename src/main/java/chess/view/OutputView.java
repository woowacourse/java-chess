package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

public class OutputView {

    public static void printInitialBoard(final Board board) {
        int i =0;
        for(Piece piece : board.getBoard().values()) {
            System.out.print(piece);
            if(i++ >6){
                i =0;
                System.out.println();
            }
        }
    }
}
