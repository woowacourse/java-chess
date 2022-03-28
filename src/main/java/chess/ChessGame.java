package chess;

import chess.domain.board.Board;
import chess.view.Command;
import chess.view.InputView;
import chess.menu.Type;
import chess.view.OutputView;

public class ChessGame {

    private final Board board = new Board();

    public void init() {
        OutputView.printGuideMessage();
        boolean play = true;

        while (play) {
            if (board.check()) {
                OutputView.printMessage("현재 check 상황입니다.");
            }
            Command command = InputView.inputCommand();
            if (board.checkmate()) {
                break;
            }
            play = convert(command);
        }
    }

    private boolean convert(Command command) {
        return Type.play(board, command);
    }
}
