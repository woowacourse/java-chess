package chess.controller.command.factory;

import chess.controller.command.command.Command;
import chess.controller.command.command.MoveCommand;

import java.util.List;

public class MoveCommandFactory implements CommandFactory {

    @Override
    public Command createCommand(final List<String> inputs) {
        return MoveCommand.from(inputs);
    }
}
