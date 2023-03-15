package chess;

import chess.domain.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        OutputView.printStartMessage();
        while (true) {
            String command = InputView.readCommand();
            if (command.equals("start")) {
                OutputView.showBoard(ChessBoard.create().pieces());
            }
            if (command.equals("end")) {
                break;
            }
        }
    }
}
