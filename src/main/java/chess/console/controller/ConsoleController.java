package chess.console.controller;

import chess.console.domain.ChessGame;
import chess.console.domain.command.ChessReady;
import chess.console.domain.command.CommandState;
import chess.console.view.InputView;
import chess.console.view.OutputView;

public class ConsoleController {

    public void run() {
        OutputView.printStartMessage();
        ChessGame chessGame = new ChessGame(inputStartOrEndCommand());

        while (!chessGame.isFinished()) {
            OutputView.printBoard(chessGame.getBoard());
            chessGame.execute(InputView.requestCommand());
        }
    }

    private CommandState inputStartOrEndCommand() {
        try {
            return ChessReady.startCommand(InputView.requestCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputStartOrEndCommand();
        }
    }
}
