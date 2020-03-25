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
            Command command = CommandReader.of(InputView.read());
            gameStatus = command.execute(gameStatus);
            OutputView.printChessBoard(gameStatus.getBoard());
        } while (gameStatus instanceof Running);
    }
}
