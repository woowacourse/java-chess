package chess;

import chess.domain.state.Ready;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame(new Ready());
        OutputView.printStartMessage();
        chessGame.run();
    }
}
