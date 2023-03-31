package chess;

import chess.controller.ControllerFactory;
import chess.controller.main.MainController;

public class Application {

    public static void main(String[] args) {
        ControllerFactory controllerFactory = ControllerFactory.getInstance();
        MainController mainController = controllerFactory.getMainController();
        mainController.run();
    }
}
