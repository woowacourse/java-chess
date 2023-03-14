package chess.controller;

import chess.model.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final GameCommand gameCommand = inputView.printGameStartMessage();

        if (GameCommand.START.equals(gameCommand)) {
            final Board board = Board.create();
            final BoardResponse boardResponse = new BoardResponse(board.getSquares());

            outputView.printChessBoard(boardResponse);
        }
    }
}
