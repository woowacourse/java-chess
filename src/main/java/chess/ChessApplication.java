package chess;

import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        ChessGame chessGame = new ChessGame(outputView);

        chessGame.play();
    }
}
