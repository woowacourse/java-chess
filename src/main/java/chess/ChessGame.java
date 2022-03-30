package chess;

import chess.domain.board.Board;
import chess.menu.Move;
import chess.view.MoveInfo;
import chess.view.InputView;
import chess.menu.MenuType;
import chess.view.OutputView;

public class ChessGame {

    private static final int MENU_INDEX = 0;

    private final Board board = new Board();

    public void init() {
        OutputView.printGuideMessage();
        boolean shouldContinue = true;

        while (shouldContinue) {
            if (board.check()) {
                OutputView.printMessage("현재 check 상황입니다.");
            }
            if (board.checkmate()) {
                break;
            }
            shouldContinue = hasNext();
        }
    }

    private boolean hasNext() {
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

    private boolean play(MenuType menuType, String[] inputValue) {
        if (menuType.isMove()) {
            return new Move().play(board, new MoveInfo(inputValue));
        }
        return menuType.play(board);
    }
}
