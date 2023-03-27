package chess.controller.command.factory;

import chess.controller.command.command.Command;
import chess.controller.command.command.StartCommand;

import java.util.List;

public class StartCommandFactory implements CommandFactory {

    @Override
    public Command createCommand(final List<String> inputs) {
        return StartCommand.create();
    }
}
