package chess.controller.command.factory;

import chess.controller.command.command.Command;
import chess.controller.command.command.EndCommand;

import java.util.List;

public class EndCommandFactory implements CommandFactory {

    @Override
    public Command createCommand(final List<String> inputs) {
        return EndCommand.create();
    }
}
