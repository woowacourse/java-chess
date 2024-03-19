package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();
        Board board = BoardFactory.createBoard();
        String command = inputView.readStartCommand();

        if (command.equals("start")) {
            outputView.printBoard(board.getPieces());
        }
    }
}
