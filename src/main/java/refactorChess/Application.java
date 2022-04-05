package refactorChess;

import refactorChess.controller.ConsoleGameController;

public class Application {
    public static void main(String[] args) {
        ConsoleGameController consoleGameController = new ConsoleGameController();
        consoleGameController.run();
    }
}
