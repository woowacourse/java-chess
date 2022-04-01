package chess;

import chess.domain.ChessGame;
import chess.domain.command.ChessReady;
import chess.domain.command.CommandState;
import chess.view.InputView;
import chess.view.OutputView;

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
