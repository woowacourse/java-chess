package chess;

import chess.domain.board.Board;
import chess.view.Command;
import chess.view.InputView;
import chess.view.Menu;
import chess.view.OutputView;

public class ChessGame {

    private final Board board = new Board();

    public void init() {
        OutputView.printGuideMessage();
        boolean play = true;

        while (play) {
            Command command = InputView.inputCommand();
            play = convert(command);
        }
    }

    private boolean convert(Command command) {
        Menu menu = command.getMenu();

        if (menu.isEnd()) {
            return false;
        }
        if (menu.isStart()) {
            start();
        }
        if (menu.isMove()) {
            move(command);
        }
        return true;
    }

    private void start() {
        board.initBoard();
        OutputView.printBoard(board.getBoard());
    }

    private void move(Command command) {
        if (board.isEmpty()) {
            OutputView.printMessage("체스 게임을 시작해야 합니다.");
            return;
        }
        board.move(command.getBeforePosition(), command.getAfterPosition());
        OutputView.printBoard(board.getBoard());
    }
}
