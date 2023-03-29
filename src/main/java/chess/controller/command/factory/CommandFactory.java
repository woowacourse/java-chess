package chess.controller.command.factory;

import chess.controller.command.command.Command;

import java.util.List;

public interface CommandFactory {

    Command createCommand(List<String> inputs);
}
