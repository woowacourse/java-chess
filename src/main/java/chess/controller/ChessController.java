package chess.controller;

import chess.controller.command.Command;
import chess.domain.board.maker.EmptyPieceGenerator;
import chess.domain.state.ChessState;
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

        ChessState chess = ChessState.start(new EmptyPieceGenerator());
        do {

            final Command command = Command.of(inputView.readGameCommand());
            chess = chess.process(command);
            outputView.printBoard(chess.getExistingPieces());

        } while (!chess.isEnd());

    }

}
