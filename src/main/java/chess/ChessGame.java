package chess;

import static chess.view.InputView.FROM_POSITION_INDEX;
import static chess.view.InputView.MENU_INDEX;
import static chess.view.InputView.TO_POSITION_INDEX;

import chess.domain.board.generator.BasicBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.board.Score;
import chess.domain.position.Position;
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
            String[] command = InputView.inputCommand();
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

    private boolean convert(String[] command) {
        Menu menu = Menu.of(command[MENU_INDEX]);

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

    private void move(String[] command) {
        if (board.isEmpty()) {
            OutputView.printStartWarning();
            return;
        }
        try {
            board.move(Position.of(command[FROM_POSITION_INDEX]), Position.of(command[TO_POSITION_INDEX]));
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
