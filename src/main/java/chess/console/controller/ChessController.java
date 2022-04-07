package chess.console.controller;


import chess.console.dto.CommandRequest;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.state.ChessGame;
import chess.domain.Color;
import chess.domain.state.Ready;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame(new Ready());

        OutputView.printStartMessage();
        while (!chessGame.isFinished()) {
            playChess(chessGame);
        }

        OutputView.printStatusMessage(chessGame.score(Color.WHITE), chessGame.score(Color.BLACK));
        OutputView.printResultMessage(chessGame.result());
    }

    private void playChess(ChessGame chessGame) {
        try {
            CommandRequest commandRequest = InputView.inputCommand();
            Command command = commandRequest.getCommand();

            command.accept(chessGame, commandRequest.getSource(), commandRequest.getTarget());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            playChess(chessGame);
        }
    }
}
