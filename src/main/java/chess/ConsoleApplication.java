package chess;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {

        OutputView.printStartMessage();
        new ChessGame().run();
    }
}
