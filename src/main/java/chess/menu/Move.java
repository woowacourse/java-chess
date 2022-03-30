package chess.menu;

import chess.domain.board.Board;
import chess.view.MoveInfo;
import chess.view.OutputView;

public class Move implements Menu {

    @Override
    public boolean play(Board board) {
        return true;
    }

    public boolean play(Board board, MoveInfo command) {
        if (board.isEmpty()) {
            OutputView.printMessage("체스 게임을 시작해야 합니다.");
            return true;
        }
        try {
            board.move(command.getBeforePosition(), command.getAfterPosition());
            OutputView.printBoard(board.getBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return true;
    }
}
