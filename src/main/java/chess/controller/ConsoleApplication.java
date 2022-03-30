package chess.controller;

import chess.controller.menu.MenuType;
import chess.controller.menu.Move;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.MoveInfo;
import chess.view.OutputView;

public class ConsoleApplication {

    private static final int MENU_INDEX = 0;
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
        MenuType menuType;
        try {
            menuType = MenuType.of(inputValue[MENU_INDEX]);
            return play(menuType, inputValue);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return hasNext();
        }
    }

    private static boolean play(MenuType menuType, String[] inputValue) {
        if (menuType.isMove()) {
            return new Move().play(board, new MoveInfo(inputValue));
        }
        return menuType.play(board);
    }
}
