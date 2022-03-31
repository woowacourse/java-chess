package chess;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        OutputView.printStartMessage();
        
        final ChessGame chessGame = new ChessGame();
        chessGame.run();
    }
}
