package chess.view;

import chess.domain.board.Board;

public class OutputView {

    public static void printInitialBoard(final Board board) {
        for(int i=0; i<8; i++){
            System.out.println(board.getLine(i));
        }
    }
}
