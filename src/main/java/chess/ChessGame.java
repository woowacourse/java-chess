package chess;

import chess.domain.board.BasicBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.Score;
import chess.view.Command;
import chess.view.InputView;
import chess.view.Menu;
import chess.view.OutputView;

public class ChessGame {

    private final Board board = new Board();
    private final BoardGenerator boardGenerator = new BasicBoardGenerator();

    public void init() {
        OutputView.printGuideMessage();
        boolean play = true;

        while (play) {
            if (check()) {
                OutputView.printCheck();
            }
            Command command = InputView.inputCommand();
            play = convert(command);
        }
    }

    private boolean check() {
        try {
            return !board.isEmpty() && board.check();
        } catch (IllegalArgumentException e) {
            return false;
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
        if (menu.isStatus()) {
            status();
        }
        return true;
    }

    private void start() {
        board.initBoard(boardGenerator);
        OutputView.printBoard(board.getBoard());
    }

    private void move(Command command) {
        if (board.isEmpty()) {
            OutputView.printStartWarning();
            return;
        }
        try {
            board.move(command.getBeforePosition(), command.getAfterPosition());
            OutputView.printBoard(board.getBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void status() {
        Score score = board.createResult();
        OutputView.printStatus(score.getValue(), score.findWinTeam());
    }
}
