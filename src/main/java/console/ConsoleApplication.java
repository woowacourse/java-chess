package console;

import console.controller.ChessConsoleController;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessConsoleController controller = new ChessConsoleController();
        controller.run();
    }
}
