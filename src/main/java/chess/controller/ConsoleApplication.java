package chess.controller;

import chess.controller.menu.Menu;
import chess.controller.menu.MenuType;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    private static final Board board = new Board();

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        OutputView.printGuideMessage();
        boolean shouldContinue = true;

        while (shouldContinue) {
            if (board.isCheck()) {
                OutputView.printMessage("현재 check 상황입니다.");
            }
            if (board.isCheckmate()) {
                break;
            }
            shouldContinue = hasNext();
        }
    }

    private static boolean hasNext() {
        String[] inputValue = InputView.inputMenu();
        Menu menu;
        try {
            menu = MenuType.of(inputValue);
            return menu.play(board);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return hasNext();
        }
    }
}
