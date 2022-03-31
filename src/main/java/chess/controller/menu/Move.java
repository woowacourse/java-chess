package chess.controller.menu;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Move implements Menu {

    private final Position before;
    private final Position after;

    public Move(String before, String after) {
        this.before = new Position(before);
        this.after = new Position(after);
    }

    @Override
    public boolean play(Board board) {
        if (board.isEmpty()) {
            OutputView.printMessage("체스 게임을 시작해야 합니다.");
            return true;
        }
        try {
            board.move(before, after);
            OutputView.printBoard(board.getBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return true;
    }
}
