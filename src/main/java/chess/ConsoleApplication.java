package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.controller.menu.Menu;
import chess.controller.menu.MenuType;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    private static ChessController chessController;

    public static void main(final String[] args) {
        OutputView.printGuideMessage();

        chessController = new ChessController(new ChessGame());
        while (chessController.isContinue()) {
            play();
        }
    }

    private static void play() {
        String[] inputValue = InputView.inputMenu();
        try {
            Menu menu = MenuType.of(inputValue);
            menu.play(chessController);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play();
        }
    }
}
