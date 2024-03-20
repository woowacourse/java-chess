package chess;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private Board board = null;

    public void run() {
        outputView.printGameStart();
        while (true) {
            String command = inputView.readCommand();
            if ("end".equalsIgnoreCase(command)) {
                return;
            }
            if ("start".equalsIgnoreCase(command)) {
                createBoard();
                continue;
            }
            move(command);
        }
    }

    private void move(String command) {
        if (board == null) {
            throw new IllegalStateException("아직 게임이 시작되지 않았습니다.");
        }
        // board.move(command)
    }

    private void createBoard() {
        board = new Board();
        outputView.printBoard(board.getBoard());
    }
}
