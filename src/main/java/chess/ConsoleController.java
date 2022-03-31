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

//    private CommandState runCommand(CommandState command) {
//        OutputView.printBoard(command.getBoard());
//        command = inputCommandAndExecute(command);
//
//        if (command.isStatus()) {
//            OutputView.printStatus(command.getStatus());
//        }
//        return command;
//    }
//
//    private CommandState inputCommandAndExecute(CommandState command) {
//        try {
//            command = command.execute(InputView.requestCommand());
//            return command;
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return inputCommandAndExecute(command);
//        }
//    }
}
