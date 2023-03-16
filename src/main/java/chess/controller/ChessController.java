package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.maker.StartingPiecesGenerator;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStartGuideMessage();
        final GameCommand command = GameCommand.findBy(inputView.readGameCommand());
        if (command == GameCommand.START) {
            final Board board = Board.createBoardWith(new StartingPiecesGenerator());
            outputView.printBoard(board.getPieces());
        }
    }
}
