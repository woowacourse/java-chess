package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.domain.gamestatus.GameStatus;
import chess.domain.gamestatus.NothingHappened;
import chess.domain.gamestatus.Running;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIApplication {
    public static void main(String[] args) {
        GameStatus gameStatus = new NothingHappened();

        OutputView.printStartInformation();

        do {
            gameStatus = runGame(gameStatus);
            OutputView.printChessBoard(gameStatus.getBoard());
        } while (gameStatus instanceof Running);
    }

    private static GameStatus runGame(GameStatus gameStatus) {
        try {
            Command command = CommandReader.of(InputView.read());
            return command.execute(gameStatus);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e);
            return runGame(gameStatus);
        }
    }
}
