package chess;

import chess.controller.Command;
import chess.controller.GameController;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStartMSG();
        String startMsg = InputView.inputCommand();
        if (Command.isStart(startMsg)) {
            GameController.start();
        }
    }
}
