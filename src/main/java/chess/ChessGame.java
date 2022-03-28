package chess;

import chess.domain.board.BasicBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.Result;
import chess.domain.piece.Team;
import chess.view.Command;
import chess.view.InputView;
import chess.view.Menu;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {

    private final Board board = new Board();
    private final BoardGenerator boardGenerator = new BasicBoardGenerator();

    public void init() {
        OutputView.printGuideMessage();
        boolean play = true;

        while (play) {
            if (board.check()) {
                OutputView.printMessage("현재 check 상황입니다.");
            }
            Command command = InputView.inputCommand();
            play = convert(command);
        }
    }

    private boolean convert(Command command) { // 이게 캡슐화 위반이다..
        Menu menu = command.getMenu();

        if (menu.isEnd() || board.checkmate()) {
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
            OutputView.printMessage("체스 게임을 시작해야 합니다.");
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
        Result score = board.createResult();
        List<Team> gameResult = score.findWinTeam();
        OutputView.printStatus(score.getValue(), gameResult);
    }
}
