package chess.controller;

import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.printGameStartInfo();
        final ChessService chessService = new ChessService();

        while (!chessService.isEnd()) {
            playChess(chessService);
            printBoard(chessService);
        }
    }

    private void printBoard(final ChessService chessService) {
        if (!chessService.isEnd()) {
            outputView.printBoard(chessService.getBoard());
        }
    }

    private void playChess(final ChessService chessService) {
        final CommandRequest commandRequest = readRequest();
        try {
            chessService.execute(commandRequest);
        } catch (Exception e) {
            outputView.printExceptionMessage(e);
            playChess(chessService);
        }
    }

    private CommandRequest readRequest() {
        try {
            return inputView.readRequest();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return readRequest();
        }
    }
}
