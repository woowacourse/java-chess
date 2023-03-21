package chess.controller;

import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printGameStartInfo();
        final ChessService chessService = new ChessService();

        while (!chessService.isEnd()) {
            playChess(chessService);
            printBoard(chessService);
        }
    }

    private static void printBoard(final ChessService chessService) {
        if (!chessService.isEnd()) {
            OutputView.printBoard(chessService.getBoard());
        }
    }

    private void playChess(final ChessService chessService) {
        final CommandRequest commandRequest = readRequest();
        try {
            chessService.execute(commandRequest);
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            playChess(chessService);
        }
    }

    private CommandRequest readRequest() {
        try {
            return InputView.readRequest();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return readRequest();
        }
    }
}
