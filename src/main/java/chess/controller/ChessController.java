package chess.controller;

import chess.controller.status.Start;
import chess.controller.status.Status;
import chess.domain.chess.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.function.Consumer;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        final ChessGame chessGame = new ChessGame();
        play(chessGame, gameStatus -> {
            if (gameStatus.isRun()) {
                OutputView.printBoard(chessGame.getChessBoard());
            }
        });
    }

    private void play(final ChessGame chessGame, Consumer<Status> consumer) {
        Status gameStatus = new Start(chessGame);
        while (gameStatus.isRun()) {
            List<String> commands = InputView.getCommand();
            final Command command = Command.findCommand(commands);
            gameStatus = gameStatus.checkCommand(command);
            consumer.accept(gameStatus);
        }
    }
}
