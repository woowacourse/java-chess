package chess.controller.command;

import chess.domain.board.Board;
import chess.dto.BoardDTO;
import chess.view.OutputView;

public abstract class AbstractCommand implements Command {

    protected final Board board;

    protected AbstractCommand(final Board board) {
        this.board = board;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    protected void printErrorMessage(String message) {
        OutputView.printErrorMessage("올바른 명령어가 아닙니다.");
        OutputView.printErrorMessage("가능한 명령어 : " + message);
    }

    protected boolean commandIsStatus(String command) {
        if ("status".equals(command)) {
            OutputView.printStatus(new BoardDTO(board));
            return true;
        }
        return false;
    }
}
