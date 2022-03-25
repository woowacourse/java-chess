package chess;

import static chess.view.OutputView.printError;

public class ConsoleApplication {

    public static void main(String[] args) {
        try {
            new ChessController().run();
        } catch (IllegalArgumentException e) {
            printError(e.getMessage());
        }
    }
}
