package chess;

import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args){
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
