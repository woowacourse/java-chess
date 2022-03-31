package chess.controller;

import chess.view.InputOption;

public class CommandFactory {

    public static Command playCommand(InputOption inputOption) {
        if (inputOption == InputOption.START) {
            return new StartCommand();
        }
        if (inputOption == InputOption.STATUS) {
            return new StatusCommand();
        }
        return new EndCommand();
    }
}
