package chess.controller.command.factory;

import chess.controller.command.command.Command;
import chess.controller.command.command.StatusCommand;

import java.util.List;

public class StatusCommandFactory implements CommandFactory {

    @Override
    public Command createCommand(final List<String> inputs) {
        return StatusCommand.create();
    }
}
