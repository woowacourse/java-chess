package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.NothingHappened;
import chess.domain.gamestate.Running;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIApplication {
    public static void main(String[] args) {
        GameState gameState = new NothingHappened();

        OutputView.printStartInformation();

        do {
            gameState = runGame(gameState);
            OutputView.printChessBoard(gameState);
        } while (gameState instanceof Running);

        OutputView.printScoreResult(gameState);
    }

    private static GameState runGame(GameState gameState) {
        try {
            Command command = CommandReader.of(InputView.read());
            return command.execute(gameState);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e);
            return runGame(gameState);
        }
    }
}
