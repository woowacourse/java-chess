package console;

import console.controller.ChessConsoleController;

public class Application {

    public static void main(String[] args) {
        ChessConsoleController controller = new ChessConsoleController();
        controller.run();
    }
}
