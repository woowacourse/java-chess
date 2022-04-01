package chess.controller;

import chess.view.InputOption;

public class CommandFactory {

    public static Command playCommand(InputOption inputOption) {
        if (inputOption == InputOption.START) {
            return new StartCommand(ChessController::showBoard);
        }
        if (inputOption == InputOption.STATUS) {
            return new StatusCommand(ChessController::showScore);
        }
        if (inputOption == InputOption.END) {
            return new EndCommand();
        }
        return new MoveCommand(ChessController::showBoard);
    }
}
