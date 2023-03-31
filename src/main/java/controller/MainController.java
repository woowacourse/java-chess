package controller;

import controller.chess.ChessController;
import controller.chess.SystemBootController;
import view.OutputView;

public class MainController {

    public void run() {
        OutputView.printStartMessage();
        ChessController controller = new SystemBootController();
        controller = controller.run();
        while (!controller.isEndController()) {
            controller = controller.run();
        }
        controller.run();
    }
}
