package chess.dto;

import chess.view.CommandType;

import java.util.List;

public record CommandInfo(CommandType type, List<String> arguments) {

    public static CommandInfo from(List<String> command) {
        CommandType commandType = CommandType.findByCommand(command);
        if (commandType.isMove()) {
            return new CommandInfo(commandType, List.of(command.get(1), command.get(2)));
        }
        return new CommandInfo(commandType, List.of());
    }
}
