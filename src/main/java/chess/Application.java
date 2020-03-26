package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static final String START = "start";

    public static void main(String[] args) {
        OutputView.printStartMSG();
        String startMsg = InputView.inputCommand();
        if (START.equals(startMsg)) {
            GameController.start();
        }
    }
}
