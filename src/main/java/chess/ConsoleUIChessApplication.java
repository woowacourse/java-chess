package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.start();
        OutputView.command();
        String command = InputView.command();
        OutputView.board();
    }
}
