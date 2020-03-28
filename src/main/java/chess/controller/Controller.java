package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.domain.gamestatus.GameStatus;
import chess.domain.gamestatus.NothingHappened;
import chess.domain.gamestatus.Running;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {

    public static void run() {
        try {
            runWithoutExceptionCatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runWithoutExceptionCatch() {
        GameStatus gameStatus = new NothingHappened();

        OutputView.printStartInformation();

        do {
            Command command = CommandReader.of(InputView.read());
            gameStatus = command.execute(gameStatus);
            OutputView.printChessBoard(gameStatus.getBoardString());
        } while (gameStatus instanceof Running);
    }
}
